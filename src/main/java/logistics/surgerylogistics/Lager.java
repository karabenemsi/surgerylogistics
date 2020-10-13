package logistics.surgerylogistics;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * Beschreiben Sie hier die Klasse Checkliste.
 * 
 * @author Burak Gülfil und Eren Coban
 * @version 11.10.2020
 */
public class Lager {
	private Operation operation;
	private ArrayList<Produktart> liste;
	private Produktart aktbestand;
	private Produktart Anzahl;

	// Konstruktor ArrayListe Produktart.
	public Lager() {
		liste = new ArrayList<Produktart>();
		ProduktartenHinzufügen();
	}

	// Konstruktor f�r Operation.
	public Lager(boolean f) // überarbeiten //NEU
	{
		liste = new ArrayList<Produktart>();
		liste.add(new Produktart("Tupfer", "TUP_70", 150, 500));
		liste.add(new Produktart("Kompressen", "KOM_80", 200, 400));
		liste.add(new Produktart("Ven.Kanüle", "KAN_15", 25, 100));
	}

	/**
	 * Methode ProduktartenHinzufügen (Zusatzauftage von Maximilian Hertle Teile
	 * 1/3)
	 *
	 */
	private void ProduktartenHinzufügen() {
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream("Produktliste.xlsx"));
			XSSFSheet sheet = workBook.getSheetAt(0);

			XSSFRow zeile;
			XSSFCell feldProduktart;
			XSSFCell feldID;
			XSSFCell feldMinAnzahl;
			XSSFCell feldMaxAnzahl;
			int i = 4;
			while (sheet.getLastRowNum() >= i) {
				zeile = sheet.getRow(i);
				if (zeile != null) {
					feldProduktart = zeile.getCell(1);
					if (feldProduktart != null) {
						feldID = zeile.getCell(2);
						feldMinAnzahl = zeile.getCell(3);
						feldMaxAnzahl = zeile.getCell(4);
						if (feldMinAnzahl != null && feldMaxAnzahl != null
								&& feldMinAnzahl.getCellType() == CellType.NUMERIC
								&& feldMaxAnzahl.getCellType() == CellType.NUMERIC) {
							String n = feldProduktart.getStringCellValue();
							String m = feldID.getStringCellValue();
							int min = (int) feldMinAnzahl.getNumericCellValue();
							int max = (int) feldMaxAnzahl.getNumericCellValue();
							liste.add(new Produktart(n, m, min, max));
						}
					}
				}
				i++;
			}

		} catch (Exception e) {
			System.out.println("Error while reading Excel file");
			System.out.println(e);
		} finally {
			if (workBook != null)
				try {
					workBook.close();
				} catch (IOException e) {
					System.out.println("Couldn't close file");
					e.printStackTrace();
				}
		}
	}

	// Methode zum Anlagen einer Operation
	// public void AnlegenOperation(String Datum, String OTA, String Arzt, String
	// Patient , String Operationstyp)
	// {

	// Datum=operation.getDatum();
	// OTA=operation.getOTA();
	// Arzt=operation.getArzt();
	// Patient=operation.getPatient();
	// Operationstyp=operation.getOperationstyp();

	// }
	public void AnlegenOperation(String Operationstyp, String Arzt, String OTA, String Patient) {
		operation = new Operation(Operationstyp, Arzt, OTA, Patient);
	}

	/**
	 * Methode prüfeBestand
	 *
	 * @return Der Rückgabewert
	 */
	public boolean prüfeBestand() {
		HashMap<String, Integer> checklisteneintraege = operation.getCheckliste();
		String name = "";
		boolean OPdurchführbar = true;
		for (int i = 0; i < liste.size(); i++) {
			name = liste.get(i).getName();
			if (checklisteneintraege.containsKey(name) == true) {
				if (liste.get(i).pruefeBestandVorOp(checklisteneintraege.get(name)) == false) {
					OPdurchführbar = false;
				}
			}
		}
		return OPdurchführbar;
	}

	// Produkte die für die OP benötigt werden und benutzt, werden abgezogen.
	public void entnehmeBestand(int verwendeteAnzahl) {
		for (Produktart v : liste) {
			int VWert = v.getAktBestand() - verwendeteAnzahl;
			v.verringereBestand(VWert);

		}
	}

	// public void entnehmeBestand() //NEU
	// {
	// HashMap <String, Integer> v= operation.getCheckliste();
	// for (Map.Entry <String, Integer> entry : liste.entrySet())
	// {
	// fw.write(" " + entry.getValue() + " " + entry.getKey() + "\n");
	// }
	// }

	/**
	 * Methode ChecklisteBearbeiten (von Maximilian Hertle)
	 *
	 * @param Verbrauchsliste Auflistung der verbrauchten Produkte mit
	 *                        Verbrauchszahlen
	 */
	public void ChecklisteBearbeiten(HashMap<String, Integer> Verbrauchsliste) {
		HashMap<String, Integer> checklisteneintraege = operation.getCheckliste();
		String name = "";
		for (int i = 0; i < liste.size(); i++) {
			name = liste.get(i).getName();
			if (Verbrauchsliste.containsKey(name) == true) {
				liste.get(i).erhoeheBestand(checklisteneintraege.get(name) - Verbrauchsliste.get(name));
			}
		}

		operation.ChecklisteBearbeiten(Verbrauchsliste);
	}

	// Bestell m�glichkeit bei unterschreitung der Mindestgrenze und bestell
	// Hinweiß.
	public String nachbestellen() {

		// for each Schleife

		for (Produktart e : liste) {
			/**
			 * if Schleife um Unterschreitung zu Pr�fen true = berechne Nachbestellmenge >
			 * berechne Zuwachswert(Zwert) > erh�he Bestand return Hinwei� auf Mindestmengen
			 * unterschreitung
			 */
			if (e.pruefeUnterschreitung() == true) {

				e.berechneNachbestellmenge();
				int ZWert = e.berechneNachbestellmenge();
				e.erhoeheBestand(ZWert);
			}

		}
		return "Mindestmenge wurde Unterschritten!";
	}

	/**
	 * Methode Auffüllen (von Maximilian Hertle)
	 *
	 * @param Auffüllliste Liste mit aufzufüllende Produkte und Anzahlen
	 */
	public void Auffüllen(HashMap<String, Integer> Auffüllliste) {
		String name = "";
		for (int i = 0; i < liste.size(); i++) {
			name = liste.get(i).getName();
			if (Auffüllliste.containsKey(name) == true) {
				liste.get(i).erhoeheBestand(Auffüllliste.get(name));
			}
		}
	}

	// Gibt die Produktarten und die aktuelle Anzahl aller Produkte, von der
	// Bestandsliste als Text wieder. 
	// return Bestandsliste
	// public String anzeigeBestandsliste()
	// {
	// return "Bestandsliste";
	// }
	public String anzeigeBestandsliste() // NEU
	{
		String bestandsliste = "BESTANDSLISTE";
		for (int i = 0; i < liste.size(); i++) {
			bestandsliste += "\n" + liste.get(i).AusgabeBestandProduktart();
		}
		return bestandsliste;
	}

	/**
	 * Get-Methode f�r OP > Gibt Op zur�ck
	 * 
	 * @return Op
	 */

	public Operation getOP() {
		return operation;
	}

	/**
	 * Set-Methode f�r OP
	 * 
	 * @param OP
	 */
	public void setOP(Operation OP) {
		operation = OP;
	}

	/**
	 * Get-Methode f�r Liste > Gibt liste zur�ck
	 * 
	 * @return liste
	 */
	public ArrayList<Produktart> getListe() {
		return liste;
	}

	/**
	 * Set-Methode f�r Liste
	 * 
	 * @param liste
	 */
	public void setListe(ArrayList<Produktart> liste) {
		this.liste = liste;
	}
}

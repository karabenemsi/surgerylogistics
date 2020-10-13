package logistics.surgerylogistics;

import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.Exception;

/**
 * Beschreiben Sie hier die Klasse Checkliste.
 * 
 * @author Maximilian Hertle und Ronny Volm
 * @version 09.10.2020
 */
public class Checklist {
	private final String nameliste;
	private HashMap<String, Integer> eintraege;

	/**
	 * Spezieller Konstruktor für Objekte der Klasse Checkliste
	 */
	public Checklist(String NameListe) {
		nameliste = NameListe;
		eintraege = new HashMap<String, Integer>();
		ChecklistenpunkteHinzufuegen();
		test();
	}

	/**
	 * Standardkonstruktor für Objekte der Klasse Checkliste
	 */
	public Checklist() {
		nameliste = "CL";
		eintraege = new HashMap<String, Integer>();
		ChecklistenpunkteHinzufuegen();
		test();
	}

	/**
	 * Methode ChecklistenpunkteHinzufuegen (Zusatzauftage von Maximilian Hertle
	 * Teile 2/3)
	 *
	 */
	private void ChecklistenpunkteHinzufuegen() {
		XSSFWorkbook workBook = null;
		try {
			workBook = new XSSFWorkbook(new FileInputStream("Produktliste.xlsx"));
			XSSFSheet sheet = workBook.getSheetAt(0);

			XSSFRow zeile = sheet.getRow(1);

			int i = 9;
			int index = 0;
			boolean operationgefunden = false;
			while (i < zeile.getLastCellNum()) // operationgefunden == false ||
			{
				if (zeile.getCell(i) != null && nameliste.equals(zeile.getCell(i).getStringCellValue())) {
					operationgefunden = true;
					index = i;
				}
				i++;
			}

			if (operationgefunden == true) {
				i = 4;
				zeile = sheet.getRow(i);
				XSSFCell feldProduktart;
				XSSFCell feldAnzahl;

				while (sheet.getLastRowNum() >= i) // feldProduktart.getCellType() != CellType.BLANK)
				{
					if (zeile != null) {
						feldProduktart = zeile.getCell(1);
						feldAnzahl = zeile.getCell(index);
						if (feldAnzahl != null) // && feldAnzahl.getCellType() != CellType.BLANK)
						{
							String s = feldProduktart.getStringCellValue();
							int f = (int) feldAnzahl.getNumericCellValue();
							eintraege.put(s, f);
						}
					}
					i++;
					zeile = sheet.getRow(i);
				}
			} else {
				// Mitteilung
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

	private void test() {

		for (Map.Entry<String, Integer> entry : eintraege.entrySet()) {
			System.out.println("x");
			System.out.println(entry.getKey() + "   " + entry.getValue());
		}
	}

	/**
	 * Ändert die Wert der einzelnen Einträge auf den jeweiligen Verbrauchswert.
	 *
	 * @param Verbrauchsliste Liste mit Produktnamen und Verbrauchsanzahl
	 */
	public void ChecklisteBearbeiten(HashMap<String, Integer> Verbrauchsliste) {
		for (Map.Entry<String, Integer> entry : Verbrauchsliste.entrySet()) {
			eintraege.put(entry.getKey(), entry.getValue());
		}
	}

	// Set-Methode setNameliste()
	// Änderung des Checklistenname ist nach einmaliger Eingabe zu Beginn nicht mehr
	// möglich.

	/**
	 * Gibt den Namen der Checkliste zurück.
	 *
	 * @return Checklistenname
	 */
	public String getNameliste() {
		return nameliste;
	}

	/**
	 * Gibt alle Einträge zurück.
	 *
	 * @return Einträge
	 */
	public HashMap<String, Integer> getEintraege() {
		return eintraege;
	}

	/**
	 * Setzt alle Einträge.
	 *
	 * @param Einträge
	 */
	public void setEintraege(HashMap Eintraege) {
		eintraege = Eintraege;
	}
}

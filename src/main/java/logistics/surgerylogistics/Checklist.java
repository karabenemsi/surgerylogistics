package logistics.surgerylogistics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
	private final SurgeryType surgeryType;
	private HashMap<String, Integer> eintraege;
	private LinkedList<ChecklistEntry> entries;

	/**
	 * Spezieller Konstruktor für Objekte der Klasse Checkliste
	 */
	public Checklist(SurgeryType surgeryType) {
		this.surgeryType = surgeryType;
		this.entries = new LinkedList<ChecklistEntry>();
		eintraege = new HashMap<String, Integer>();
		ChecklistenpunkteHinzufuegen();
		test();
	}

	/**
	 * Standardkonstruktor für Objekte der Klasse Checkliste
	 */
	public Checklist() {
		this.surgeryType = SurgeryType.Bypass;
		this.entries = new LinkedList<ChecklistEntry>();
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
		ExcelReader reader = new ExcelReader();
		this.entries = reader.readChecklistEntriesfromFile("Produktliste.xlsx", this.surgeryType);
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
	public SurgeryType getSurgeryType() {
		return this.surgeryType;
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
	public void setEintraege(HashMap<String, Integer> Eintraege) {
		eintraege = Eintraege;
	}

}

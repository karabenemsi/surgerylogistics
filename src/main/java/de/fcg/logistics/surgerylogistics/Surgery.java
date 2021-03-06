package de.fcg.logistics.surgerylogistics;

import java.util.HashMap;
import java.util.Map;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.io.*;

public class Surgery {
	private LocalDateTime date;
	private SurgeryType surgeryType;
	private String surgeon;
	private String ota;
	private String patient;
	private Checklist checklist;

	public Surgery(SurgeryType surgeryType, String surgeon, String ota, String patient) {
		this.date = LocalDateTime.now();
		this.surgeon = surgeon;
		this.ota = ota;
		this.patient = patient;
		setSurgeryType(surgeryType);
	}

	public Surgery() throws Exception {
		this.date= LocalDateTime.now();
		this.surgeon = "Dr. Schmid";
		this.ota = "G.Maier";
		this.patient = "Müller";
		this.surgeryType = SurgeryType.Bypassoperation;
		checklist = new Checklist(surgeryType);
	}

	public void speicherDaten() {
		SimpleDateFormat Format1 = new SimpleDateFormat("yyyyMMdd");
		String datumformat1 = Format1.format(date);
		File ordner = new File("Operationen/" + datumformat1);
		if (ordner.exists() == false) {
			ordner.mkdirs();
		}

		SimpleDateFormat Format2 = new SimpleDateFormat("yyyyMMdd-hhmm");
		String datumformat2 = Format2.format(date);
		File datei = new File("Operationen/" + datumformat1 + "/" + datumformat2 + "_" + patient + ".txt");

		if (datei.exists() == false) {
			SimpleDateFormat Format3 = new SimpleDateFormat("dd.MM.yyyy - hh:mm");
			String datumformat3 = Format3.format(date);
			try {
				datei.createNewFile();
				FileWriter fw = new FileWriter(datei);
				fw.write("Datum: " + datumformat3 + "\n\n");
				fw.write("Patientenname: " + patient + "\n");
				fw.write("dokumentierende OTA: " + ota + "\n");
				fw.write("durchführender Arzt: " + surgeon + "\n\n");
				fw.write("Operation: " + surgeryType + "\n\n");
				fw.write("Verbrauchsliste:\n");
				fw.write(checklist.toString());
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setSurgeryType(SurgeryType surgeryType) {
			checklist = new Checklist(surgeryType);
			this.surgeryType = surgeryType;
	}

	@Override
	public String toString() {
		return "Surgery [date=" + date + ", surgeryType=" + surgeryType + ", surgeon=" + surgeon + ", ota=" + ota
				+ ", patient=" + patient + ", checklist=" + checklist.toString() + "]";
	}

}
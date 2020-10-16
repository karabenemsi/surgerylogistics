package de.fcg.logistics.surgerylogistics;

import java.util.LinkedList;

public class Checklist {
	private final SurgeryType surgeryType;
	private LinkedList<ChecklistEntry> entries;

	public Checklist(SurgeryType surgeryType) {
		this.surgeryType = surgeryType;
		this.entries = new LinkedList<ChecklistEntry>();
		this.importList();
	}

	public Checklist() {
		this.surgeryType = SurgeryType.Bypassoperation;
		this.entries = new LinkedList<ChecklistEntry>();
		this.importList();
	}

	private void importList() {
		ExcelReader reader = new ExcelReader();
		this.entries = reader.readChecklistEntriesfromFile("Produktliste.xlsx", this.surgeryType);
	}

	public SurgeryType getSurgeryType() {
		return this.surgeryType;
	}

	public LinkedList<ChecklistEntry> getEntries() {
		return entries;
	}

	public void setEntries(LinkedList<ChecklistEntry> entries) {
		this.entries = entries;
	}

	@Override
	public String toString() {
		System.out.println(entries.size());
		String str = "Checklist:\n";
				str+= "surgeryType=" + surgeryType + "\n";
				str+= "Entries=";

				str += entries.stream().map(e -> e.getProduct().toString() + "\n").reduce("", String::concat);
		return str;
	}

}

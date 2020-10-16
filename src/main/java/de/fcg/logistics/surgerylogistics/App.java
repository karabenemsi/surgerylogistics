package de.fcg.logistics.surgerylogistics;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Storage storage = Storage.getInstance();
		storage.logStorage();
		
		Surgery s = new Surgery(SurgeryType.Bypassoperation, "Surgeon", "OTA", "Patient");
		System.out.println(s.toString());
	}

}

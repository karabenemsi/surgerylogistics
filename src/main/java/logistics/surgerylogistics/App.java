package logistics.surgerylogistics;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Storage lager = new Storage();

		lager.AnlegenOperation("Crazy Operation", "Arzt", "OTA", "Patient");

		System.out.println(lager.anzeigeBestandsliste());
	}

}

package logistics.surgerylogistics;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		Storage storage = Storage.getInstance();
		System.out.println(storage.anzeigeBestandsliste());
	}

}

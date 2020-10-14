package logistics.surgerylogistics;


import java.util.HashMap;
import java.util.LinkedList;


/**
 * Beschreiben Sie hier die Klasse Checkliste.
 * 
 * @author Burak Gülfil und Eren Coban
 * @version 11.10.2020
 */
public final class Storage {
	private Surgery surgery;
	private LinkedList<Product> products;
	
	private static Storage instance = new Storage();

	// Konstruktor ArrayListe Produktart.
	private Storage() {
		this.products = new LinkedList<Product>();
		this.importProducts();
	}
	
	public static Storage getInstance() {
		return instance;
	}

	// Konstruktor für Operation.
	private Storage(boolean f) // überarbeiten //NEU
	{
		products = new LinkedList<Product>();
		products.add(new Product("Tupfer", "TUP_70", 150, 500));
		products.add(new Product("Kompressen", "KOM_80", 200, 400));
		products.add(new Product("Ven.Kanüle", "KAN_15", 25, 100));
	}

	/**
	 * Methode ProduktartenHinzufügen (Zusatzauftage von Maximilian Hertle Teile
	 * 1/3)
	 *
	 */
	private void importProducts() {
		ExcelReader reader = new ExcelReader();
		this.products = reader.readProductsfromFile("Produktliste.xlsx");
	}


	// Bestellmöglichkeit bei unterschreitung der Mindestgrenze und bestell
	// Hinweiß.
	public String nachbestellen() {

		// for each Schleife

		for (Product e : products) {
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
		for (int i = 0; i < products.size(); i++) {
			name = products.get(i).getName();
			if (Auffüllliste.containsKey(name) == true) {
				products.get(i).erhoeheBestand(Auffüllliste.get(name));
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
		for (int i = 0; i < products.size(); i++) {
			bestandsliste += "\n" + products.get(i).AusgabeBestandProduktart();
		}
		return bestandsliste;
	}

	/**
	 * Get-Methode f�r OP > Gibt Op zur�ck
	 * 
	 * @return Op
	 */

	public Surgery getOP() {
		return surgery;
	}

	/**
	 * Set-Methode f�r OP
	 * 
	 * @param OP
	 */
	public void setOP(Surgery OP) {
		surgery = OP;
	}


	public Product findProductById(String s) {
		// TODO Auto-generated method stub
		return null;
	}
}

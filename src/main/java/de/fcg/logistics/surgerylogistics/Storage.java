package de.fcg.logistics.surgerylogistics;


import java.util.HashMap;
import java.util.LinkedList;


public final class Storage {
	private Surgery surgery;
	private HashMap<String, Product> products;
	private LinkedList<Order> orders;
	
	private static Storage instance = new Storage();

	// Konstruktor ArrayListe Produktart.
	private Storage() {
		this.products = new LinkedList<Product>();
		this.importProducts();
	}
	
	public static Storage getInstance() {
		return instance;
	}

	private void importProducts() {
		ExcelReader reader = new ExcelReader();
		this.products = reader.readProductsfromFile("Produktliste.xlsx");
	}
	
	public boolean isAllStocked() {
		boolean isStocked = true;
		for( Product p : this.products) {
			isStocked = !p.isStockUnderMinimum();
		}
		return isStocked;
	}
	
	public Order createOrder() {
		return new Order();
	}
	
	public void fullFillOrder() {
		
	}
	
	public void logStorage() {
		System.out.println("Current Storage:");
		for(Product p : this.products) {
			System.out.println(p.toString());
		}
	}


	public Product findProductById(String id) {
		for (Product p: this.products) {
			if(p.getID() == id) return p;
		}
	}
}

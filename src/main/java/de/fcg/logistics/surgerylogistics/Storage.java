package de.fcg.logistics.surgerylogistics;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

public final class Storage {
	private HashMap<String, Product> products;
	private LinkedList<Order> orders;

	private static Storage instance = new Storage();

	private Storage() {
		this.products = new HashMap<String, Product>();
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
		return this.products.entrySet().stream().map(entry -> !entry.getValue().isStockUnderMinimum()).reduce(true,
				(a, b) -> a && b);
	}

	public void createOrder() {
		Order order = new Order();
		for (Entry<String, Product> pe : this.products.entrySet()) {
			Product p = pe.getValue();
			if (p.isStockUnderMinimum())
				order.addEntry(p.getID(), p.getAmountToOrder());
		}
		this.orders.add(order);
	}

	public void fullFillOrder(Order order) {
		for (Entry<String, Integer> oe : order.getOrderMap().entrySet()) {
			this.products.get(oe.getKey()).increaseStock(oe.getValue());
		}
	}

	public void logStorage() {
		System.out.println("Current Storage:");
		for (Entry<String, Product> pe : this.products.entrySet()) {
			pe.getValue().toString();
		}
	}

	public Product findProductById(String id) {
		return this.products.get(id);
	}
}

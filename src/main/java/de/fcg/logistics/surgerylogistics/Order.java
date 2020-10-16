package de.fcg.logistics.surgerylogistics;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Order {
	private boolean fullfilled;
	private final LocalDateTime dateCreated;
	private LocalDateTime dateFullfilled;
	private HashMap<String, Integer> orderMap;


	public Order() {
		this.fullfilled = false;
		this.dateCreated = LocalDateTime.now();
		this.orderMap = new HashMap<String, Integer>();
	}

	public void addEntry(String id, int amount) {
		this.orderMap.put(id, amount);
	}
	
	public LocalDateTime getDateFullfilled() {
		return dateFullfilled;
	}

	public HashMap<String, Integer> getOrderMap() {
		return orderMap;
	}

	public void setFullfilled() {
		this.fullfilled = true;
		this.dateFullfilled = LocalDateTime.now();
	}
	
	public boolean isFullfilled() {
		return this.fullfilled;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	

}

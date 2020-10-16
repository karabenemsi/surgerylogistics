package de.fcg.logistics.surgerylogistics;

public class Product {

	private String name;
	private String id;
	private int stock;
	private int minimumStock;
	private int maximumStock;

	public Product(String name, String id, int minimumStock, int maximumStock) {
		this.name = name;
		this.id = id;
		this.stock = maximumStock;
		this.minimumStock = minimumStock;
		this.maximumStock = maximumStock;
	}

	public Product() {
		name = "Kanuele";
		id = "Kan_07";
		stock = 60;
		minimumStock = 40;
		maximumStock = 100;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

		public String getID() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public int getStock() {
		return stock;
	}
	
	public boolean isAmountInStock(int amount) {
		return this.stock >= amount;
	}
	
	public void reduceStock(int amount) throws IllegalStockException {
		if(this.isAmountInStock(amount)) {
			this.stock -= amount;
		} else {
			throw new IllegalStockException("Can't reduce Stock by " + amount + ". Not enough products available");
		}
	}
	
	public int increaseStock(int amount){
		int added = 0;
		try {
			added = this.increaseStock(amount, true);
		} catch(IllegalStockException e) {
			
		}
		return added;
	}
	
	public int increaseStock(int amount, boolean fillToLimit) throws IllegalStockException{
		int amountAddedToStock = 0;
		if ( this.stock + amount > this.maximumStock) {
			if(fillToLimit) {
				amountAddedToStock = this.maximumStock - this.stock;
				this.stock = this.maximumStock;
			} else {
				throw new IllegalStockException("Can't increase Stock by " + amount + ". Will be more than maximum " + this.maximumStock);
			}
		} else {
			this.stock += amount;
			amountAddedToStock=amount;
		}
		return amountAddedToStock;
	}
	
	public boolean isStockUnderMinimum() {
		return this.stock < this.minimumStock;
	}
	
	public int getAmountToOrder() {
		return this.maximumStock - this.stock;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", id=" + id + ", stock=" + stock + ", minimumStock=" + minimumStock
				+ ", maximumStock=" + maximumStock + "]";
	}
	
	
	
}
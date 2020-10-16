package de.fcg.logistics.surgerylogistics;

public class ChecklistEntry {
    private int amountPlaned;
    private int amountUsed;


	private Product product;
    
    public ChecklistEntry(Product product, int amountPlaned) {
    	this.product = product;
    	this.amountPlaned = amountPlaned;
    }

    private boolean isInStock(){
    	return this.product.isAmountInStock(this.amountPlaned);
    }

    public Product getProduct(){
      return this.product;
    }

    public int getAmountPlaned(){
      return this.amountPlaned;
    }
    
    public int getAmountUsed() {
		return amountUsed;
	}

	public void setAmountUsed(int amountUsed) {
		this.amountUsed = amountUsed;
	}

	public void setAmountPlaned(int amountPlaned) {
		this.amountPlaned = amountPlaned;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}

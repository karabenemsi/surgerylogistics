package logistics.surgerylogistics;

public class ChecklistEntry {
    private int amountPlaned;
    private int amountUsed;
    private Product product;
    
    public ChecklistEntry(Product product, int amountPlaned) {
    	this.product = product;
    	this.amountPlaned = amountPlaned;
    }

    private boolean checkStock(){
      return true;
    }

    public Product getProduct(){
      return this.product;
    }

    public int getAmountPlaned(){
      return this.amountPlaned;
    }
}

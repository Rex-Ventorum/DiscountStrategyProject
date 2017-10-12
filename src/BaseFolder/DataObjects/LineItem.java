package BaseFolder.DataObjects;

public class LineItem {
    private String productId;
    private int quantity;
    
    //-------------------//
    //--- Constructor ---//
    //-------------------//
    public LineItem(String productId, int quantity){
        setProductId(productId);
        setQuantity(quantity);
    }
  
    //----------------------//
    //--- Setter Methods ---//
    //----------------------//
    
    public final void setProductId(String productId){
        if(productId == null)
            throw new IllegalArgumentException("Product Id May Not Be Null");
        this.productId = productId;
    }
    
    public final void setQuantity(int quantity){
        if(quantity < 0) throw new IllegalArgumentException("Quantity May Not Be Negtivie");
        this.quantity = quantity;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//

    public final String getProductId() {
        return productId;
    }

    public final int getQuantity() {
        return quantity;
    }
    
}

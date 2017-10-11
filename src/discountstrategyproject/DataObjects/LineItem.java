package discountstrategyproject.DataObjects;

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
        if(productId == null || !productId.matches("[A-Z]\\d{3}"))
            throw new IllegalArgumentException("Product Id Must Match A### Format");
        this.productId = productId;
    }
    
    public final void setQuantity(int quantity){
        if(quantity < 0) throw new IllegalArgumentException("Quantity May Not Be Negtivie");
        this.quantity = quantity;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }
    
}

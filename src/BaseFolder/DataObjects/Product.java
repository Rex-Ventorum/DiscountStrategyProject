package BaseFolder.DataObjects;

import BaseFolder.DataObjects.Discounts.Discount;
import java.util.Objects;

public class Product {
    private String productId;
    private String productName;
    private double unitCost;
    private Discount discount;
    
    //-------------------//
    //--- Constructor ---//
    //-------------------/
    public Product(String productId, String productName, double unitCost, Discount discount){
        setProductId(productId);
        setProductName(productName);
        setUnitCost(unitCost);
        setDiscount(discount);
    }
    
    //----------------------//
    //--- Setter Methods ---//
    //----------------------//
    
    public final void setProductId(String productId){
        if(productId == null)
            throw new IllegalArgumentException("Product ID May Not Be Null");
        this.productId = productId;
    }
    
    public final void setProductName(String productName){
        if(productName == null) throw new IllegalArgumentException("Product name must be non-null");
        this.productName = productName;
    }
    
    public final void setUnitCost(double unitCost){
        if(unitCost < 0) throw new IllegalArgumentException("Unit cost must be a postive number");
        this.unitCost = unitCost;
    }
    
    public final void setDiscount(Discount discount){
        if(discount == null) throw new IllegalArgumentException("Discount must be non-null");
        this.discount = discount;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//

    public final String getProductId() {
        return productId;
    }

    public final String getProductName() {
        return productName;
    }

    public final double getUnitCost() {
        return unitCost;
    }

    public final Discount getDiscount() {
        return discount;
    }
    
    //----------------------//
    //- Functional Methods -//
    //----------------------//
    
    public final double calculateDiscount(int quantity){
        return discount.calculateDiscount(unitCost, quantity);
    }
    
    public final double calculateSavings(int quantity){
        return discount.calculateSavings(unitCost, quantity);
    }
    
    //-------------------------//
    //--- Overriden Methods ---//
    //-------------------------//

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.productId);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (!Objects.equals(this.productId, other.productId)) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString(){
        return productId + ": " + productName;
    }
    
 
}

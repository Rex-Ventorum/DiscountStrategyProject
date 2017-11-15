
package BaseFolder.DataObjects;

import BaseFolder.CustomExceptions.NullValueInArrayException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Receipt {
    public static final int MAX_ITEMS_PER_SALE = 100;
    
    private LocalDateTime timeStamp;
    private String sellerName;
    private String customerId;
    private List<LineItem> lineItems;
    
    //----------------------//
    //---- Constructor -----//
    //----------------------//
    
    public Receipt(String sellerName){
        this(sellerName, "N/A");
    }
    public Receipt(String sellerName, String customerId){
        this(LocalDateTime.now(),sellerName,customerId);
    }
    
    public Receipt(LocalDateTime timeStamp,String sellerName, String customerId){
        this(timeStamp,sellerName,customerId,new LinkedList<>());
    }
    
    public Receipt(LocalDateTime timeStamp,String sellerName,String customerId, List<LineItem> lineItems){
        setTimeStamp(timeStamp);    setSellerName(sellerName);
        setCustomerId(customerId);  setLineItems(lineItems);
    }
    
    //----------------------//
    //- Functional Methods -//
    //----------------------//
    
    public final boolean addProduct(String productId, int quantity){
        testAddRemoveArgumentValilidy(productId,quantity); //Will crash here if invalid
        boolean itemWasAdded = false;
        
        //Check if product already exsists
        LineItem existing = null;
        for(LineItem item : lineItems){
            if(item.getProductId().equals(productId)){
                existing = item; break;
            }
        }
        
        //if product already on recipt add quanity to lineItem
        if(existing != null){
            existing.setQuantity(existing.getQuantity() + quantity);
            itemWasAdded = true;
        //Else Add New Item if lineItems not alreay At Max
        }else{
           if(lineItems.size() < MAX_ITEMS_PER_SALE){
               lineItems.add(new LineItem(productId, quantity));
               itemWasAdded = true;
           }
        }
        return itemWasAdded;
    }
    
    public final boolean removeProduct(String productId){return removeProduct(productId,Integer.MAX_VALUE);}
    public final boolean removeProduct(String productId, int quantity){
        testAddRemoveArgumentValilidy(productId,quantity); //Will crash here if invalid
        boolean itemWasRemoved = true;
        
        //Check for existing item
        LineItem existing = null;
        for(int i =0; i<lineItems.size(); i++){
            LineItem searchItem = lineItems.get(i);
            if(searchItem.getProductId().equals(productId)){
                existing = searchItem; break;
            }
        }
        
        //if product already on recipt duduct quanity from lineItem or remove it alltogether
        if(existing != null){
            //Remove completly if quantity is greater or equal to existing quanity
            if(existing.getQuantity() <= quantity){
                lineItems.remove(existing);
            //Subtract Quantity if product exists and deduction would leave non negitive non zero amount
            }else{
                existing.setQuantity(existing.getQuantity()-quantity);
                itemWasRemoved = false;
            }
        }
        return itemWasRemoved;
    }
    
    //----------------------//
    //--- Setter Methods ---//
    //----------------------//
    public final void setTimeStamp(LocalDateTime date){
        if(date == null || dateIsInFuture(date))
            throw new IllegalArgumentException("Date May Not be Null or In the Future");
        timeStamp = date;
    }
    
    public final void setSellerName(String sellerName){
        if(sellerName == null)
            throw new IllegalArgumentException("Seller Name May Not Be Null");
        this.sellerName = sellerName;
    }
    
    public final void setCustomerId(String customerId){
        if(customerId == null)
            throw new IllegalArgumentException("Customer ID May Not Be Null");
        this.customerId = customerId;
    }
    
    public final void setLineItems(LineItem[] lineItems){
        if(lineItems == null || lineItems.length > MAX_ITEMS_PER_SALE)
            throw new IllegalArgumentException("Line Items May Not Be Null and may not exceed length of " + MAX_ITEMS_PER_SALE);
        if(nullItemInArray(lineItems)) throw new NullValueInArrayException();
        this.lineItems.clear();
        for(LineItem item : lineItems) this.lineItems.add(item);
    }
    
    public final void setLineItems(List<LineItem> lineItems){
        if(lineItems == null || lineItems.size() > MAX_ITEMS_PER_SALE)
            throw new IllegalArgumentException("Line Items May Not Be Null and may not exceed length of " + MAX_ITEMS_PER_SALE);
        if(lineItems.contains(null)) throw new NullValueInArrayException();
        this.lineItems = lineItems;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//
    public final LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public final String getSellerName() {
        return sellerName;
    }

    public final String getCustomerId() {
        return customerId;
    }

    public final List<LineItem> getLineItems() {
        return lineItems;
    }
    
    //----------------------//
    //- Validation Methods -//
    //----------------------//
    
    private final boolean dateIsInFuture(LocalDateTime testDate){
        return testDate.isAfter(LocalDateTime.now());
    }
    
    private final void testAddRemoveArgumentValilidy(String productId, int quantity){
        if(productId == null) throw new IllegalArgumentException("Product Id May Not Be Null");
        if(quantity < 0) throw new IllegalArgumentException("Quantity may not be negitive");
    }
    
    private final boolean nullItemInArray(Object[] array){
        for(Object item : array)  if(item == null) return true;
        return false;
    }
    
    //-------------------------//
    //--- Overriden Methods ---//
    //-------------------------//

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.timeStamp);
        hash = 23 * hash + Objects.hashCode(this.customerId);
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
        final Receipt other = (Receipt) obj;
        if (!Objects.equals(this.customerId, other.customerId)) {
            return false;
        }
        if (!Objects.equals(this.timeStamp, other.timeStamp)) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString(){
        return sellerName + " Sale Receipt Object";
    }
}

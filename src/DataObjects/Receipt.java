
package DataObjects;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class Receipt {
    public static final int MAX_ITEMS_PER_SALE = 100;
    
    private Date timeStamp;
    private String sellerName;
    private String customerId;
    private LineItem[] lineItems;
    
    //----------------------//
    //---- Constructor -----//
    //----------------------//
    
    public Receipt(String sellerName, String customerId){
        this(Calendar.getInstance().getTime(),sellerName,customerId);
    }
    
    public Receipt(Date timeStamp,String sellerName, String customerId){
        this(timeStamp,sellerName,customerId,new LineItem[]{});
    }
    
    public Receipt(Date timeStamp,String sellerName,String customerId, LineItem[] lineItems){
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
           if(lineItems.length < MAX_ITEMS_PER_SALE){
               LineItem[] newArray = Arrays.copyOf(lineItems, lineItems.length+1);
               newArray[lineItems.length] = new LineItem(productId, quantity);
               lineItems = newArray;
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
        int removalIndex = 0;
        for(int i =0; i<lineItems.length; i++){
            if(lineItems[i].getProductId().equals(productId)){
                existing = lineItems[i]; removalIndex = i; break;
            }
        }
        
        //if product already on recipt duduct quanity from lineItem or remove it alltogether
        if(existing != null){
            //Remove completly if quantity is greater or equal to existing quanity
            if(existing.getQuantity() <= quantity){
                LineItem[] newArray = new LineItem[lineItems.length-1];
                for(int i=0; i<newArray.length; i++){
                    if(i < removalIndex) newArray[i] = lineItems[i];
                    else newArray[i] = lineItems[i+1];
                }//end of for loop
                lineItems = newArray;
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
    public final void setTimeStamp(Date date){
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
        this.lineItems = lineItems;
    }
    
    //----------------------//
    //--- Getter Methods ---//
    //----------------------//
    public final Date getTimeStamp() {
        return timeStamp;
    }

    public final String getSellerName() {
        return sellerName;
    }

    public final String getCustomerId() {
        return customerId;
    }

    public final LineItem[] getLineItems() {
        return lineItems;
    }
    
    //----------------------//
    //- Validation Methods -//
    //----------------------//
    
    private final boolean dateIsInFuture(Date testDate){
        Date today = Calendar.getInstance().getTime();
        return testDate.after(today);
    }
    
    private final void testAddRemoveArgumentValilidy(String productId, int quantity){
        if(productId == null) throw new IllegalArgumentException("Product Id May Not Be Null");
        if(quantity < 0) throw new IllegalArgumentException("Quantity may not be negitive");
    }
}

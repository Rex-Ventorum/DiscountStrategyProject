package BaseFolder.DataObjects.Discounts;

public class BuyXGetXFreeDiscount implements Discount{

    private int buyAmount;
    private int freeAmount;
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    
    public BuyXGetXFreeDiscount(int buyAmount, int freeAmount){
        setBuyAmount(buyAmount); setFreeAmount(freeAmount);
    }
    
    //-------------------------//
    //--- Interface Methods ---//
    //-------------------------//
    
    @Override
    public final double calculateDiscount(double unitCost, int quantity) {
        return (unitCost * quantity) - calculateSavings(unitCost, quantity);
    }

    @Override
    public final double calculateSavings(double unitCost, int quantity) {
       double quantityIgnored = 0;
       
       while(quantity > 0){
           quantity -= buyAmount;
           for(int i=0; i<freeAmount; i++){
               if(quantity > 0){
                   quantityIgnored++;
                   quantity--;
               }else{
                   break;  
               }//end of if
           }//end of for
       }//end of while
       return unitCost * quantityIgnored;       
    }
    
    //-------------------------//
    //---- Setter Methods -----//
    //-------------------------//
    
    public final void setBuyAmount(int buyAmount) {
        if(buyAmount < 1) throw new IllegalArgumentException("Amount may not be lower than 1");
        this.buyAmount = buyAmount;
    }

    public final void setFreeAmount(int freeAmount) {
        if(freeAmount < 1) throw new IllegalArgumentException("Amount may not be lower than 1");
        this.freeAmount = freeAmount;
    }
    
    //-------------------------//
    //---- Getter Methods -----//
    //-------------------------//

    public final int getBuyAmount() {
        return buyAmount;
    }

    public final int getFreeAmount() {
        return freeAmount;
    }


    
}

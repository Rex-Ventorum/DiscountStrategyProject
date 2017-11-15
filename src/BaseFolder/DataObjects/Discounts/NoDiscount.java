package BaseFolder.DataObjects.Discounts;

public class NoDiscount implements Discount{

    @Override
    public final double calculateDiscount(double unitCost, int quantity) {
        return unitCost * quantity;
    }

    @Override
    public final double calculateSavings(double unitCost, int quantity) {
        return 0;
    }
    
    @Override
    public final boolean equals(Object object){
        return object instanceof NoDiscount;
    }
    
    @Override
    public final String toString(){
        return "A No Discount Discount Object";
    }
}

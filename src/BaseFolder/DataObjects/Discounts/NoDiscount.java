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
    
}

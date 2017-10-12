package BaseFolder.DataObjects.Discounts;

public interface Discount {
    public abstract double calculateDiscount(double unitCost, int quantity);
    public abstract double calculateSavings(double unitCost, int quantity);
}

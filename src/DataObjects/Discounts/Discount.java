package DataObjects.Discounts;

public interface Discount {
    public abstract double calculateDiscount(double unitCost, int quantity);
    public abstract double calculateDiscountAmount(double unitCost, int quantity);
}

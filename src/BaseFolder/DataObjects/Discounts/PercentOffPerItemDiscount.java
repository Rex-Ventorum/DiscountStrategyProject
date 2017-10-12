package BaseFolder.DataObjects.Discounts;

public class PercentOffPerItemDiscount implements Discount{

    private double percentOff;
    
    public PercentOffPerItemDiscount(double percentOff){
        setPercentOff(percentOff);
    }
    
    public final void setPercentOff(double percentOff){
        if(percentOff < 0 || percentOff > 1) throw new IllegalArgumentException("Percent Off must be a postive decimal");
        this.percentOff = percentOff;
    }
    
    public final double getPercentOff(){
        return percentOff;
    }
    
    @Override
    public final double calculateDiscount(double unitCost, int quantity) {
        return (unitCost * quantity) - calculateSavings(unitCost, quantity);
    }

    @Override
    public final double calculateSavings(double unitCost, int quantity) {
        return (unitCost*percentOff) * quantity;
    }
   
}

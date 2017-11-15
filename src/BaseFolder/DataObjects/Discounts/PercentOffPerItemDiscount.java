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
    
    //-------------------------//
    //--- Overriden Methods ---//
    //-------------------------//

    @Override
    public final int hashCode() {
        int hash = 5;
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.percentOff) ^ (Double.doubleToLongBits(this.percentOff) >>> 32));
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
        final PercentOffPerItemDiscount other = (PercentOffPerItemDiscount) obj;
        if (Double.doubleToLongBits(this.percentOff) != Double.doubleToLongBits(other.percentOff)) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString(){
        return "A " + (percentOff*100) + "% off Discount";
    }
   
}

package BaseFolder.ReceiptFormatterInterface;

import BaseFolder.DataObjects.Customer;
import BaseFolder.DataObjects.Receipt;
import BaseFolder.DataObjects.LineItem;
import BaseFolder.DataObjects.Product;
import BaseFolder.DatabaseInterface.DatabaseStrategy;

public class FullReceiptFormat implements ReceiptFormatter{
    private static final String NEW_LINE = "\n";
    private static final String LINE_BREAK = "---------------------------------------------------------------" + NEW_LINE;
    
    private DatabaseStrategy database;
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    
    public FullReceiptFormat(DatabaseStrategy database){
        setDatabase(database);
    }
    //-------------------------//
    //--- Interface Methods ---//
    //-------------------------//
    
    @Override
    public final String getFormatedReciptString(Receipt receipt) {
        String formatedReciptString = "";
        formatedReciptString += getFormatedHeading(receipt);
        formatedReciptString += getFormatedBody(receipt);
        formatedReciptString += getFormatedFooter(receipt);
        return formatedReciptString;
    }

    //-------------------------//
    //----- Helper Methods ----//
    //-------------------------//
    
    
    //---- HEADING HELPER -----//
    private final String getFormatedHeading(Receipt receipt){
        String formatedHeading = "";
        String title = "Thank You For Shoppig At " + receipt.getSellerName();
        formatedHeading +=  centerString(title,LINE_BREAK.length())+ NEW_LINE;
        formatedHeading += LINE_BREAK;
        
        Customer customer = database.findCustomer(receipt.getCustomerId());
        if(customer != null){
            formatedHeading += "Customer Name: " + customer.getFullName();
        }else{
            formatedHeading += "Customer Name: N/A";
        }
       
        formatedHeading += NEW_LINE;
        formatedHeading += "Time of Sale: " + receipt.getTimeStamp().toString() + NEW_LINE;
        formatedHeading += LINE_BREAK;
        
        return formatedHeading;
    }
    
    //----- BODY HELPER -------//
    private final String getFormatedBody(Receipt receipt){
        int prodIdCol = 4; int nameCol = 15;
        int cashCol = 7;   int qntCol = 3;
        int discountCol = 8;
        
        String space = " | ";
        String prodIdColHead = "|  ID ";
        String nameColHead = "   Item Name   ";
        String itemCostColHead = "Itm Amt";
        String qntColHead = "QNT";
        String subAmtColHead = "Sub Amt";
        String discountColHead = "Discount";
        
        String formatedBody = prodIdColHead + space + nameColHead + space + itemCostColHead + space + 
                qntColHead + space + discountColHead + space + subAmtColHead + space + NEW_LINE + LINE_BREAK;
        
        for(LineItem item : receipt.getLineItems()){
            Product product = database.findProduct(item.getProductId());
            if(product != null){
                String itemString = "| "; 
                itemString += setStringToLength(product.getProductId(),prodIdCol,true) + space;
                itemString += setStringToLength(product.getProductName(),nameCol,true) + space;            
                itemString += setStringToLength(getCashStringFromDouble(product.getUnitCost()),cashCol,true) + space;
                itemString += setStringToLength(item.getQuantity()+"",qntCol,true) + space;
                
                double savings = product.calculateSavings(item.getQuantity());
                itemString += setStringToLength(getCashStringFromDouble(savings),discountCol,true) + space;
                
                double subAmount = product.calculateDiscount(item.getQuantity());
                itemString += setStringToLength(getCashStringFromDouble(subAmount),cashCol,true) + space;
                
                itemString += NEW_LINE;
                formatedBody += itemString;
            }//end of non-null product check
        }
        
        return formatedBody + LINE_BREAK;
    }
    
    //----- FOOTER HELPER -----//
    private final String getFormatedFooter(Receipt receipt){
        String formatedFooter = "";
        int cashCol = 10;
        double grossTotal = 0;
        double discountTotal = 0;
        double grandTotal = 0;
        
        String grossTotalString = "Grand Total: ";
        String discountTotalString = "Discount Total: ";
        String grandTotalString = "Amount Owed: ";
        
        for(LineItem item : receipt.getLineItems()){
            Product product = database.findProduct(item.getProductId());
            if(product != null){
                grossTotal += product.getUnitCost() * item.getQuantity();
                discountTotal += product.calculateSavings(item.getQuantity());
            }
        }
        grandTotal = grossTotal - discountTotal;
        
        formatedFooter += "|" + setStringToLength(grossTotalString, LINE_BREAK.length()-cashCol-1, false);
        formatedFooter += setStringToLength(getCashStringFromDouble(grossTotal), cashCol-2,true) + "|" + NEW_LINE;
        
        formatedFooter += "|" + setStringToLength(discountTotalString, LINE_BREAK.length()-cashCol-1, false);
        formatedFooter += setStringToLength(getCashStringFromDouble(discountTotal), cashCol-2,true) + "|" + NEW_LINE;
        
        formatedFooter += LINE_BREAK;
        
        formatedFooter += "|" + setStringToLength(grandTotalString, LINE_BREAK.length()-cashCol-1, false);
        formatedFooter += setStringToLength(getCashStringFromDouble(grandTotal), cashCol-2,true) + "|" + NEW_LINE;
        
        formatedFooter += LINE_BREAK;
        
        return formatedFooter;
    }
    
    private final String setStringToLength(String string, int length, boolean padAfter){
        if(string.length() > length) return string.substring(0,length);
        else {
            String longerString = string;
            int diffrence = length - string.length();
            if(padAfter) for(int i=0; i<diffrence; i++) longerString += " ";
            else for(int i=0; i<diffrence; i++) longerString = " " + longerString;
            return longerString;
        }
    }
    
    private final String getCashStringFromDouble(double num){
        double roundOff = Math.round(num * 100.0) / 100.0;
        return "$" +  roundOff;
    }
    
    private final String centerString(String string, int charWidth){
        if(string.length() >= charWidth) return string.substring(0,charWidth);
        else{
            String centered = string;
            int charPad = (charWidth - string.length())/2;
            for(int i=0; i<charPad; i++){
               centered =  " " + centered + " ";
            }
            if((charPad % 2 != 0)) centered = centered.substring(1,centered.length());
            return centered;
        }
    }
    
    //-------------------------//
    //-- Get and Set Methods --//
    //-------------------------//
    
    public final void setDatabase(DatabaseStrategy database) {
        if(database == null) throw new IllegalArgumentException("Database is required for this format");
        this.database = database;
    }
        
    public final DatabaseStrategy getDatabase() {
        return database;
    } 
    
}

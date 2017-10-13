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
    
    public FullReceiptFormat(DatabaseStrategy database){
        setDatabase(database);
    }
    
    @Override
    public final String getFormatedReciptString(Receipt receipt) {
        String formatedReciptString = "";
        formatedReciptString += getFormatedHeading(receipt);
        formatedReciptString += getFormatedBody(receipt);
        return formatedReciptString;
    }

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
        String discountColHead = "You Save";
        
        String formatedBody = prodIdColHead + space + nameColHead + space + itemCostColHead + space + 
                qntColHead + space + subAmtColHead + space + discountColHead + space + NEW_LINE + LINE_BREAK;
        
        for(LineItem item : receipt.getLineItems()){
            Product product = database.findProduct(item.getProductId());
            if(product != null){
                String itemString = "| "; 
                itemString += setStringToLength(product.getProductId(),prodIdCol) + space;
                itemString += setStringToLength(product.getProductName(),nameCol) + space;            
                itemString += setStringToLength(getCashStringFromDouble(product.getUnitCost()),cashCol) + space;
                itemString += setStringToLength(item.getQuantity()+"",qntCol) + space;
                
                double subAmount = product.calculateDiscount(item.getQuantity());
                itemString += setStringToLength(getCashStringFromDouble(subAmount),cashCol) + space;
                
                double savings = product.calculateSavings(item.getQuantity());
                itemString += setStringToLength(getCashStringFromDouble(savings),discountCol) + space;
                itemString += NEW_LINE;
                formatedBody += itemString;
            }//end of non-null product check
        }
        
        return formatedBody + LINE_BREAK;
    }
    
    private final String setStringToLength(String string, int length){
        if(string.length() > length) return string.substring(0,length);
        else {
            String longerString = string;
            int diffrence = length - string.length();
            for(int i=0; i<diffrence; i++) longerString += " ";
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
    
    
    public final void setDatabase(DatabaseStrategy database) {
        if(database == null) throw new IllegalArgumentException("Database is required for this format");
        this.database = database;
    }
        
    public final DatabaseStrategy getDatabase() {
        return database;
    } 
    
}

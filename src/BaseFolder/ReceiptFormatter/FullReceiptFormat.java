package BaseFolder.ReceiptFormatter;

import BaseFolder.DataObjects.Customer;
import BaseFolder.DataObjects.Receipt;
import BaseFolder.DataObjects.LineItem;
import BaseFolder.DataObjects.Product;
import BaseFolder.DatabaseInterface.DatabaseStrategy;

public class FullReceiptFormat implements ReceiptFormatter{
    private static final String NEW_LINE = "\n";
    private static final String LINE_BREAK = "---------------------------" + NEW_LINE;
    
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
        formatedHeading += "Thank You For Shoppig At " + receipt.getSellerName() + NEW_LINE;
        formatedHeading += LINE_BREAK;
        
        Customer customer = database.findCustomer(receipt.getCustomerId());
        if(customer != null){
            formatedHeading += "Customer Name: " + customer.getFullName();
        }else{
            formatedHeading += "--- CASH SALE ---";
        }
       
        formatedHeading += NEW_LINE;
        formatedHeading += "Time of Sale: " + receipt.getTimeStamp().toString();
        formatedHeading += LINE_BREAK;
        
        return formatedHeading + NEW_LINE;
    }
    
    private final String getFormatedBody(Receipt receipt){
        String formatedBody = "";
        
        for(LineItem item : receipt.getLineItems()){
            Product product = database.findProduct(item.getProductId());
            if(product != null){
                String itemString = "";
                itemString += product.getProductName() + "";
                itemString += 
            }//end of non-null product check
        }
        
        return formatedBody;
    }
    
    private final String setStringToLength(String string, int length){
        if(string.length() > length) return string.substring(0,length);
        else 
    }
    
    public final void setDatabase(DatabaseStrategy database) {
        if(database == null) throw new IllegalArgumentException("Database is required for this format");
        this.database = database;
    }
        
    public final DatabaseStrategy getDatabase() {
        return database;
    } 
    
}

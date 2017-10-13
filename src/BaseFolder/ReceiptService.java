package BaseFolder;

import BaseFolder.DataObjects.Receipt;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;

public class ReceiptService {
    private ReceiptFormatter formater;
    private Receipt receipt;
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    
    public ReceiptService(ReceiptFormatter formater){
        setReceiptFormatter(formater);
    }
    
    //-------------------------//
    //-- Functional Methods ---//
    //-------------------------//
    
    public final void startNewTransaction(String customerId, String sellersName){
        receipt = new Receipt(customerId,sellersName);
    }
    
    public final void addProduct(String productId, int quantity){
        receipt.addProduct(productId, quantity);
    }
    
    public final void removeProduct(String productId, int quantity){
        receipt.removeProduct(productId, quantity);
    }
    
    public final String getFormatedReceiptString(){
        return formater.getFormatedReciptString(receipt);
    }
    
    //-------------------------//
    //---- Setter Methods -----//
    //-------------------------//
    
    public final void setReceiptFormatter(ReceiptFormatter formater){
        if(formater == null) throw new IllegalArgumentException("Formater may not be null");
        this.formater = formater;
    }
    
    public final void setReceipt(Receipt receipt){
        if(receipt == null) throw new IllegalArgumentException("Receipt may not be null");
        this.receipt = receipt;
    }
    
    //-------------------------//
    //---- Getter Methods -----//
    //-------------------------//

    public final ReceiptFormatter getFormater() {
        return formater;
    }

    public final Receipt getReceipt() {
        return receipt;
    }
    
}

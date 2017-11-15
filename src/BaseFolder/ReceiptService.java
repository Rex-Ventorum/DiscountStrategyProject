package BaseFolder;

import BaseFolder.DataObjects.Receipt;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;
import java.util.Objects;
import java.util.UUID;

public class ReceiptService {
    
    private String receiptServiceId;
    private ReceiptFormatter formater;
    private Receipt receipt;
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    
    public ReceiptService(ReceiptFormatter formater){
        setReceiptFormatter(formater);
        receipt = new Receipt(null);
        receiptId = UUID.randomUUID().toString();
    }
    
    //-------------------------//
    //-- Functional Methods ---//
    //-------------------------//
    
    public final void startNewTransaction(String sellersName){
        receipt = new Receipt(sellersName);
    }
    
    public final void addProduct(String productId, int quantity){
        receipt.addProduct(productId, quantity);
    }
    
    public final void removeProduct(String productId, int quantity){
        receipt.removeProduct(productId, quantity);
    }
    
    public final void finishTransaction(String customerId){
        receipt.setCustomerId(customerId);
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
    
    public final void setReceiptServiceID(String receiptServiceId) throws IllegalArgumentException{
        if(receiptServiceId == null || receiptServiceId.isEmpty() || receiptServiceId.length() < 8) 
            throw new IllegalArgumentException("Receipt Service Id must be at least length of 8");
        this.receiptServiceId = receiptServiceId;
    }
    
    //-------------------------//
    //---- Getter Methods -----//
    //-------------------------//

    public final ReceiptFormatter getReceiptFormatter() {
        return formater;
    }

    public final Receipt getReceipt() {
        return receipt;
    }
    
    public final String getReceiptServiceId(){
        return receiptServiceId;
    }
    
    //-------------------------//
    //--- Overriden Methods ---//
    //-------------------------//

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.receiptServiceId);
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
        final ReceiptService other = (ReceiptService) obj;
        if (!Objects.equals(this.receiptServiceId, other.receiptServiceId)) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString(){
        return "This Is A Receipt Service Object";
    }
    
}

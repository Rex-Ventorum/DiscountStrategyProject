package BaseFolder;

import BaseFolder.OutputSourceInterface.OutputSource;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;

public class POSTerminal {
    private static final String NO_TRANSACTION_ERROR_MSG = "Transaction Has Not Been Started yet";
    
    //FOR INTERNAL USE ONLY
    private final ReceiptService receiptService;
    private boolean transactionStarted;
    
    //CONFIGURABLE
    private OutputSource outputSource;
    private String sellerName; 
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    public POSTerminal(String sellerName, ReceiptFormatter formatter, OutputSource outputSource) {
        receiptService = new ReceiptService(formatter);
        transactionStarted = false;
        setOutputSource(outputSource);
        setSellerName(sellerName);
    }
    
    //-------------------------//
    //-- Functional Methods ---//
    //-------------------------//
    
    public final void startNewTransaction(){
        receiptService.startNewTransaction(sellerName);
        transactionStarted = true;
    }
    
    public final void addProductToTransaction(String productId, int quantity) throws IllegalStateException{
        if(transactionStarted) receiptService.addProduct(productId, quantity);
        else throw new IllegalStateException(NO_TRANSACTION_ERROR_MSG);
    }
    
    public final void removeProductFromTransaction(String productId, int quantity) throws IllegalStateException{
        if(transactionStarted) receiptService.removeProduct(productId, quantity);
        else throw new IllegalStateException(NO_TRANSACTION_ERROR_MSG);
    }
    
    public final void finishTransaction(String customerId) throws IllegalStateException{
        if(!transactionStarted) throw new IllegalStateException(NO_TRANSACTION_ERROR_MSG);
        receiptService.finishTransaction(customerId);
        outputSource.outputData(receiptService.getFormatedReceiptString());
        transactionStarted = false;
    }
    
    //-------------------------//
    //---- Setter Methods -----//
    //-------------------------//
    
    public final void setOutputSource(OutputSource outputSource){
        if(outputSource == null) throw new IllegalArgumentException("Output Source May Not Be Null");
        this.outputSource = outputSource;
    }
    
    public final void setReceiptFormatter(ReceiptFormatter formatter){
        receiptService.setReceiptFormatter(formatter);
    }
    
    public final void setSellerName(String sellerName){
        if(sellerName == null) throw new IllegalArgumentException("Seller Name May Not Be Null");
        this.sellerName = sellerName;
    }
    
    //-------------------------//
    //---- Getter Methods -----//
    //-------------------------//

    public final boolean transactionHasStarted(){
        return transactionStarted;
    }
    
    public final OutputSource getOutputSource() {
        return outputSource;
    }
    
    public final ReceiptFormatter getReceiptFormatter(){
        return receiptService.getReceiptFormatter();
    }

    public final String getSellerName(){
        return sellerName;
    }
}

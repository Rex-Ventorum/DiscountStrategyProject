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
    
    //-------------------------//
    //-- Constructor Methods --//
    //-------------------------//
    public POSTerminal(ReceiptFormatter formatter, OutputSource outputSource) {
        receiptService = new ReceiptService(formatter);
        transactionStarted = false;
        setOutputSource(outputSource);
    }
    
    //-------------------------//
    //-- Functional Methods ---//
    //-------------------------//
    
    public final void startNewTransaction(String sellerName){
        receiptService.startNewTransaction(sellerName);
        transactionStarted = true;
    }
    
    public final void addProductToTransaction(String productId, int quantity){
        if(transactionStarted) receiptService.addProduct(productId, quantity);
        else throw new IllegalArgumentException(NO_TRANSACTION_ERROR_MSG);
    }
    
    public final void removeProductFromTransaction(String productId, int quantity){
        if(transactionStarted) receiptService.removeProduct(productId, quantity);
        else throw new IllegalArgumentException(NO_TRANSACTION_ERROR_MSG);
    }
    
    public final void finishTransaction(String customerId){
        if(!transactionStarted) throw new IllegalArgumentException(NO_TRANSACTION_ERROR_MSG);
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
    
    //-------------------------//
    //---- Getter Methods -----//
    //-------------------------//

    public final OutputSource getOutputSource() {
        return outputSource;
    }
    
    public final ReceiptFormatter getReceiptFormatter(){
        return receiptService.getReceiptFormatter();
    }

}

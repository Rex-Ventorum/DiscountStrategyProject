package BaseFolder;

import BaseFolder.OutputSourceInterface.OutputSource;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;
import java.util.Objects;
import java.util.UUID;

public class POSTerminal {
    private static final String NO_TRANSACTION_ERROR_MSG = "Transaction Has Not Been Started yet";
    
    //FOR INTERNAL USE ONLY
    private String terminalId;
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
        terminalId = UUID.randomUUID().toString();
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
    
    public final void setOutputSource(OutputSource outputSource) throws IllegalArgumentException{
        if(outputSource == null) throw new IllegalArgumentException("Output Source May Not Be Null");
        this.outputSource = outputSource;
    }
    
    public final void setReceiptFormatter(ReceiptFormatter formatter) throws IllegalArgumentException{
        if(outputSource == null) throw new IllegalArgumentException("Formatter May Not Be Null");
        receiptService.setReceiptFormatter(formatter);
    }
    
    public final void setSellerName(String sellerName) throws IllegalArgumentException{
        if(sellerName == null) throw new IllegalArgumentException("Seller Name May Not Be Null");
        this.sellerName = sellerName;
    }
    
    public final void setTerminalId(String terminalId) throws IllegalArgumentException{
        if(terminalId == null || terminalId.isEmpty() || terminalId.length() < 8) 
            throw new IllegalArgumentException("Terminal Id must be at least length of 8");
        this.terminalId = terminalId;
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
    
    public final String getTerminalId(){
        return terminalId;
    }
    
    //-------------------------//
    //--- Overriden Methods ---//
    //-------------------------//

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.terminalId);
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
        final POSTerminal other = (POSTerminal) obj;
        if (!Objects.equals(this.terminalId, other.terminalId)) {
            return false;
        }
        return true;
    }
    
    @Override
    public final String toString(){
        return "This Is A POS Terminal Owned by : " + sellerName;
    }
    
}

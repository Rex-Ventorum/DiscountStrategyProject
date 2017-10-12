package BaseFolder.ReceiptFormatter;
import BaseFolder.DataObjects.Receipt;

public interface ReceiptFormatter {
    public abstract String getFormatedReciptString(Receipt receipt);
}

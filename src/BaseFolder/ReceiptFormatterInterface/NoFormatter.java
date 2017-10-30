package BaseFolder.ReceiptFormatterInterface;

import BaseFolder.DataObjects.Receipt;

public class NoFormatter implements ReceiptFormatter{
    @Override public String getFormatedReciptString(Receipt receipt) {return "";} 
}

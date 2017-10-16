package BaseFolder.ReceiptFormatterInterface;

import BaseFolder.DataObjects.LineItem;
import BaseFolder.DataObjects.Receipt;


public class InvitoryFormat implements ReceiptFormatter{
    private static final String NEW_LINE = "\n";

    @Override
    public String getFormatedReciptString(Receipt receipt) {
        String formattedRecipt = ""; int charWidth = 15;
        formattedRecipt += "Invitory Report" + NEW_LINE;
        formattedRecipt += "---------------" + NEW_LINE;
        formattedRecipt += "|  ID  | QNT  |" + NEW_LINE;;
        for(LineItem lineItem : receipt.getLineItems()){
            String line = "| ";
            line += lineItem.getProductId() + " | " + lineItem.getQuantity();
            for(int i=line.length(); i<charWidth-1; i++) line += " ";
            formattedRecipt += line + "|" + NEW_LINE;
        }
        formattedRecipt += "---------------";
        return formattedRecipt;
    }
   
}

package BaseFolder;

import BaseFolder.DatabaseInterface.InMemoryDatabase;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;
import BaseFolder.ReceiptFormatterInterface.FullReceiptFormat;
import BaseFolder.DataObjects.Receipt;

public class StartUp {
    // NO DEFAULTS TO STRATAGIE ITEMS ASIDE FROM DISCOUNTS WHICH SHOULD BE HARD CODED
    // DONT CHAIN GETTERS AND SETTERS TO SET STRATAGIE OBJECTS (WHITE BOX VS BLACK BOX) BLACK BOX IS BETER!
    public static void main(String[] args) {
        ReceiptFormatter formatter = new FullReceiptFormat(new InMemoryDatabase());
        Receipt receipt = new Receipt("Kohls", "");
        receipt.addProduct("M102", 5);
        receipt.addProduct("M103", 3);
        receipt.addProduct("W102", 1);
        System.out.println(formatter.getFormatedReciptString(receipt));
    }
}

package BaseFolder;

import BaseFolder.DatabaseInterface.*;
import BaseFolder.ReceiptFormatterInterface.ReceiptFormatter;
import BaseFolder.ReceiptFormatterInterface.FullReceiptFormat;
import BaseFolder.OutputSourceInterface.*;

public class StartUp {
    // NO DEFAULTS TO STRATAGIE ITEMS ASIDE FROM DISCOUNTS WHICH SHOULD BE HARD CODED
    // DONT CHAIN GETTERS AND SETTERS TO SET STRATAGIE OBJECTS (WHITE BOX VS BLACK BOX) BLACK BOX IS BETER!
    public static void main(String[] args) {
        DatabaseStrategy database = new InMemoryDatabase();
        ReceiptFormatter formatter = new FullReceiptFormat(database);
        
        OutputSource out1 = new CounsolOutputSource();
        OutputSource out2 = new GUIOutputSource();
        OutputSource mulitOutput = new MulitOutputSource(out1, out2);
        
        POSTerminal terminal = new POSTerminal("Kohls",formatter,mulitOutput);

        //Transaction 1
        terminal.startNewTransaction();
        terminal.addProductToTransaction("M102", 5);
        terminal.addProductToTransaction("M103", 3);
        terminal.addProductToTransaction("W102", 1);
        terminal.finishTransaction("BD001");
        
        //Transaction 2
        terminal.startNewTransaction();
        terminal.addProductToTransaction("F110", 3);
        terminal.addProductToTransaction("W103", 2);
        terminal.finishTransaction("");
        
        //Transaction 3
        terminal.startNewTransaction();
        terminal.addProductToTransaction("M103", 5);
        terminal.addProductToTransaction("M102", 3);
        terminal.addProductToTransaction("W103", 5);
        terminal.addProductToTransaction("W102", 3);
        terminal.addProductToTransaction("T010", 1);
        terminal.addProductToTransaction("F110", 5);
        terminal.addProductToTransaction("F112", 2);
        terminal.finishTransaction("SJ001");
    }
}

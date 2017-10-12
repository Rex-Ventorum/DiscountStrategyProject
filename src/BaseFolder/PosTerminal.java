/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseFolder;

import BaseFolder.ReceiptFormatter.ReceiptFormatter;

/**
 *
 * @author L117student
 */
public class PosTerminal {
    private ReceiptService receiptService;
    
    public PosTerninal(ReceiptFormatter formatter) {
        receiptService = new ReceiptService(formatter);
    }
}

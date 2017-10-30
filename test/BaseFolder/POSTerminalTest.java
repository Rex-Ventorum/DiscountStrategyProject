package BaseFolder;

import BaseFolder.OutputSourceInterface.NoOutputSource;
import BaseFolder.ReceiptFormatterInterface.NoFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class POSTerminalTest {
    
    private POSTerminal instance;
    
    public POSTerminalTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        instance = new POSTerminal("", new NoFormatter(), new NoOutputSource());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void startTransactionSetsFlagToTrue(){
        instance.startNewTransaction();
        assertTrue(instance.transactionHasStarted());
    }
    
    @Test (expected = IllegalStateException.class)
    public void addProductWithoutStartingTransactionThrowsError(){
        instance.addProductToTransaction("ID", 5);
    }
    
    @Test (expected = IllegalStateException.class)
    public void removeProductWithoutStartingTransactionThrowsError(){
        instance.removeProductFromTransaction("ID", 3);
    }
    
    @Test (expected = IllegalStateException.class)
    public void finishTransactionWithoutStartingTransactionThrowsError(){
        instance.finishTransaction("ID");
    }
    
    @Test
    public void canAddProductAfterStartTransaction(){
        instance.startNewTransaction();
        instance.addProductToTransaction("ID", 5);
    }
    
    @Test
    public void canRemoveProductAfterStartTransaction(){
        instance.startNewTransaction();
        instance.removeProductFromTransaction("ID", 4);
    }
    
    @Test
    public void canFinishTransactionAfterStartTransaction(){
        instance.startNewTransaction();
        instance.finishTransaction("ID");
    }
    
    @Test
    public void finishTransactionSetsFlagToFalse(){
        instance.startNewTransaction();
        instance.finishTransaction("ID");
        assertFalse(instance.transactionHasStarted());
    }
}

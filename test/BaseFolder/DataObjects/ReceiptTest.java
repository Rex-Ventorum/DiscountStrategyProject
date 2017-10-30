package BaseFolder.DataObjects;

import BaseFolder.CustomExceptions.NullValueInArrayException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReceiptTest {
    
    private Receipt instance;
    private static final String DUMMY_SELLER = "Dummy Seller";
    private static final String DUMMY_PRODUCT_1 = "ID1";
    private static final String DUMMY_PRODUCT_2 = "ID2";
   
    public ReceiptTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
        //ALL TESTS USE MINIMAL CONSTRUCTOR THUS ASSUMING RECEIPT IS "NEW"
        instance = new Receipt(DUMMY_SELLER);
    }
    
    //Helper For some tests
    private void setLineItemsToMax(){
        LineItem[] lineItems = new LineItem[Receipt.MAX_ITEMS_PER_SALE];
        for(int i=0; i<lineItems.length; i++){
            lineItems[i] = new LineItem("TEST", 5);
        }
        instance.setLineItems(lineItems);
    }
    
    
    @After
    public void tearDown() {
    }
    
    // Test Constructor //
    
    @Test
    public void minimalConstructorCreatesEmptyReceipt(){
        assertEquals(instance.getLineItems().length,0);
        assertEquals(instance.getSellerName(),DUMMY_SELLER);
    }
    
    // Test Argument Validity //
    
    @Test (expected = IllegalArgumentException.class)
    public void addProductThrowsErrorWithNullProduct(){
        instance.addProduct(null, 1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void addProductThrowsErrorWithNegitiveQuantity(){
        instance.addProduct(DUMMY_PRODUCT_1, -1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void removeProductThrowsErrorWithNullProduct(){
        instance.addProduct(null, 1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void removeProductThrowsErrorWithNegitiveQuantity(){
        instance.addProduct(DUMMY_PRODUCT_1, -1);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setSellerNameThrowsErrorWhenNull(){
        instance.setSellerName(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setCustomerIdTThrowsErrorWhenNull(){
        instance.setCustomerId(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setLineItemsThrowsErrorWhenNull(){
        instance.setLineItems(null);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setLineItemsThrowsErrorWithArrayLargerThanMax(){
        LineItem[] lineItems = new LineItem[Receipt.MAX_ITEMS_PER_SALE+1];
        for(int i=0; i<lineItems.length; i++){
            lineItems[i] = new LineItem("TEST", 5);
        }
        instance.setLineItems(lineItems);
    }
    
    @Test (expected = NullValueInArrayException.class)
    public void setLineItemsThrowsErroWithNullItemInArray(){
        instance.setLineItems(new LineItem[1]);
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setTimeStampThrowsErrorWithFutureDate(){
        //DO SOMETHING
        throw new IllegalArgumentException("DUMMY EXCEPTION");
    }
    
    @Test (expected = IllegalArgumentException.class)
    public void setTimeStampThrowsErrorWithNullDate(){
        instance.setTimeStamp(null);
    }
    
    // Test Function Validity //
    
    @Test 
    public void addProductCreatesNewLineItemWithNewProducts(){
        int lineItemsQntBefore = instance.getLineItems().length;
        instance.addProduct(DUMMY_PRODUCT_1, 1);
        assertEquals(lineItemsQntBefore+1, instance.getLineItems().length);
        instance.addProduct(DUMMY_PRODUCT_2,5);
        assertEquals(lineItemsQntBefore+2,instance.getLineItems().length);
    }
    
    @Test
    public void addProductAddsToExistingLineItemsWhenProductSame(){
        instance.addProduct(DUMMY_PRODUCT_1, 1);
        instance.addProduct(DUMMY_PRODUCT_2, 2);
        int lineItemsQntBefore = instance.getLineItems().length;
        instance.addProduct(DUMMY_PRODUCT_1, 1);
        instance.addProduct(DUMMY_PRODUCT_2, 2);
        int lineItemsQntAfter = instance.getLineItems().length;
        
        int expectedProduct1Qnt = 2;
        int actualProduct1Qnt = -1;//Default will insure if product not found test fails
        
        int expectedProduct2Qnt = 4;
        int actualProduct2Qnt = -1;//Default will insure if product not found test fails
        
        LineItem[] lineItems = instance.getLineItems();
        for(LineItem lineItem : lineItems){
            if(lineItem.getProductId().equals(DUMMY_PRODUCT_1)){
               actualProduct1Qnt = lineItem.getQuantity();
            }
            if(lineItem.getProductId().equals(DUMMY_PRODUCT_2)){
               actualProduct2Qnt = lineItem.getQuantity();
            }
        }//end of for loop
        
        assertEquals(lineItemsQntBefore,lineItemsQntAfter);
        assertEquals(expectedProduct1Qnt,actualProduct1Qnt);
        assertEquals(expectedProduct2Qnt,actualProduct2Qnt);
    }
    
    @Test
    public void removeProductNoQntRemovesProductComplelty(){
        int lineItemsQntBefore = instance.getLineItems().length;
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        instance.removeProduct(DUMMY_PRODUCT_1);
        int lineItemsQntAfter = instance.getLineItems().length;
        
        for(LineItem lineItem : instance.getLineItems()){
            if(lineItem.getProductId().equals(DUMMY_PRODUCT_1)) fail("Item Not Removed");
        }
        
        assertEquals(lineItemsQntBefore,lineItemsQntAfter);
    }
    
    @Test
    public void removeProductLargerQntThanExistingRemovesProductCompletly(){
        int lineItemsQntBefore = instance.getLineItems().length;
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        instance.removeProduct(DUMMY_PRODUCT_1,50);
        int lineItemsQntAfter = instance.getLineItems().length;
        
        for(LineItem lineItem : instance.getLineItems()){
            if(lineItem.getProductId().equals(DUMMY_PRODUCT_1)) fail("Item Not Removed");
        }
        
        assertEquals(lineItemsQntBefore,lineItemsQntAfter);
    }
    
    @Test
    public void removeProductSmallerQntThanExistingSubtractsProductQnt(){
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        instance.removeProduct(DUMMY_PRODUCT_1,1);
        
        int expectedProductQnt = 4;
        int actualProductQnt = -1;
        for(LineItem lineItem : instance.getLineItems()){
            if(lineItem.getProductId().equals(DUMMY_PRODUCT_1)){
                actualProductQnt = lineItem.getQuantity();
            }
        }
        
        assertEquals(expectedProductQnt,actualProductQnt);
    }
    
    @Test 
    public void removeProductReturnsFalseWhenProductQntSubtractedButNotRemoved(){
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        assertFalse(instance.removeProduct(DUMMY_PRODUCT_1,2));
    }
    
    @Test 
    public void removeProductReturnsTrueWhenProductRemoved(){
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        assertTrue(instance.removeProduct(DUMMY_PRODUCT_1));
    }
    
    @Test
    public void addProductReturnsTrueWhenProductAdded(){
        assertTrue(instance.addProduct(DUMMY_PRODUCT_1, 4));
    }
    
    @Test
    public void addProductReturnsFalseWhenProductMaxReached(){
        setLineItemsToMax();
        assertFalse(instance.addProduct(DUMMY_PRODUCT_1, 4));
    }
    
    @Test
    public void addProductDoesNotAddItemWhenProductMaxReached(){
        setLineItemsToMax();
        int lineItemsQntBefore = instance.getLineItems().length;
        instance.addProduct(DUMMY_PRODUCT_1, 5);
        int lineItemsQntAfter = instance.getLineItems().length;
        assertEquals(lineItemsQntBefore,lineItemsQntAfter);
    }
    

    
}

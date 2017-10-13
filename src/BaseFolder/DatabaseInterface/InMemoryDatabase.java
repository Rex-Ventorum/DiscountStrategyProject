package BaseFolder.DatabaseInterface;

import BaseFolder.DataObjects.Customer;
import BaseFolder.DataObjects.Product;
import BaseFolder.DataObjects.Discounts.*;

public class InMemoryDatabase implements DatabaseStrategy{
    private static final Customer[] CUSTOMER_DATABASE = {
        new Customer("SJ001","Sally", "Jane"),
        new Customer("SJ002","Sam","Johnson"),
        new Customer("BD001","Brandon", "Dopp"),
        new Customer("CR001","Cody", "Ross")
    };
    
    private static final Product[] PRODUCT_DATABASE = {
       new Product("M102", "Mens T-Shirt", 5.00, new NoDiscount()),
       new Product("M103", "Mens Long Pants", 7.00, new PercentOffPerItemDiscount(.25)),   
       new Product("W102", "Womens T-Shirt", 5.00, new NoDiscount()),
       new Product("W103", "Womens Long Pants", 7.00, new PercentOffPerItemDiscount(.25))
    };
    
    @Override
    public final Customer findCustomer(String customerId) {
        Customer existing = null;
        for(Customer customer : CUSTOMER_DATABASE){
            if(customer.getCustomerId().equals(customerId)){
                existing = customer; break;
            }
        }
        return existing;
    }

    @Override
    public final Product findProduct(String productId) {
        Product existing = null;
        for(Product product : PRODUCT_DATABASE){
            if(product.getProductId().equals(productId)){
                existing = product; break;
            }
        }
        return existing;
    }
    
}

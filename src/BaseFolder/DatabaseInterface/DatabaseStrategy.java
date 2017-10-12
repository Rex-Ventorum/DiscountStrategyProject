package BaseFolder.DatabaseInterface;

import BaseFolder.DataObjects.Customer;
import BaseFolder.DataObjects.Product;

public interface DatabaseStrategy {
    public abstract Customer findCustomer(String customerId);
    public abstract Product findProduct(String productId);
}

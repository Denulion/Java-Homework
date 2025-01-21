import java.util.HashMap;
import java.util.Map;
import exceptions.*;

public class ProductStorage {
    private Map<String, Product> products;

    public ProductStorage() {
        this.products = new HashMap<>();
    }

    public Product getProduct(String name) throws SoldOutException {
        if (this.products.containsKey(name)) {
            return this.products.get(name);
        }
        throw new SoldOutException("Product not available!");
    }

    public void displayProducts() {
        System.out.println("Available Products:");
        this.products.forEach((key, product) -> System.out.println(product.getName()
                + ": $" + product.getPrice() + " (Quantity: " + product.getQuantity() + ")"));
    }
}

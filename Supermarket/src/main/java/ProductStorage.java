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

    public boolean hasProduct(String name) {
        return this.products.containsKey(name);
    }

    public void addProduct(String name, Product product) {
        this.products.put(name, product);
    }

    public void minusProduct(String name, int amount) {
        Product product = this.products.get(name);

        if (product.getQuantity() - amount == 0){
            this.products.remove(name);
        } else {
            int newQuantity = product.getQuantity() - amount;
            product.setQuantity(newQuantity);
        }
    }

    public void displayProducts() {
        this.products.forEach((key, product) -> System.out.println(product.getName()
                + ": $" + product.getPrice() + " (Quantity: " + product.getQuantity() + ")"));
    }
}

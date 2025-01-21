import exceptions.*;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void reduceQuantity(int amount) throws SoldOutException {
        if (amount > this.quantity) {
            throw new SoldOutException(this.name + " is out of stock!");
        }
        this.quantity -= amount;
    }
}

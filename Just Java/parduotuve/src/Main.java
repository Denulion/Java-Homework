public class Main {
    public static void main(String[] args) {
        Store store = new Store();

        store.addProduct(new SimpleProduct("Bread", 1.0));
        store.addProduct(new Medicine("Painkillers", 5.0));
        store.addProduct(new Alcohol("Vodka", 10.0, 40.0));
        store.addProduct(new Wine("Red Wine", 8.0, 12.0));

        store.listProducts();
    }
}
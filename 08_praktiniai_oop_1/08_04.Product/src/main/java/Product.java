public class Product {
    private String name;
    private int qty;
    private double price;

    public Product(String name, double price, int qty){
        this.name = name;
        this.price = price;
        this.qty = qty;
    }
    public void printProduct(){
        System.out.println(name + ", price " + price + ", " + qty + " pcs");
    }
}

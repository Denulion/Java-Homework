public abstract class Product {
    private String name;
    private double nettoCost;

    public Product(String name, double nettoCost) {
        this.name = name;
        this.nettoCost = nettoCost;
    }
    public String getName() {
        return name;
    }
    public double getNettoCost() {
        return nettoCost;
    }
    public abstract double calculateFinalPrice();

    @Override
    public String toString() {
        return "Product{name='" + name + "', nettoCost=" + nettoCost + '}';
    }
}
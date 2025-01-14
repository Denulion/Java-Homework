public class Medicine extends Product {
    private static final double tax = 0.09;

    public Medicine(String name, double nettoCost) {
        super(name, nettoCost);
    }
    @Override
    public double calculateFinalPrice() {
        return getNettoCost() * (1 + tax);
    }
}
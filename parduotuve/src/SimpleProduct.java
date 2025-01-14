public class SimpleProduct extends Product{
    private static final double tax = 0.21; // НДС 21%

    public SimpleProduct(String name, double nettoCost) {
        super(name, nettoCost);
    }

    @Override
    public double calculateFinalPrice() {
        return getNettoCost() * (1 + tax);
    }
}

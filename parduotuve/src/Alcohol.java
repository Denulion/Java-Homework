public class Alcohol extends Product{
    private double alcohol;
    private static final double tax = 0.21;
    private static final double lowTax = 0.89;
    private static final double highTax = 1.26;

    public Alcohol(String name, double nettoCost, double alcohol) {
        super(name, nettoCost);
        this.alcohol = alcohol;
    }

    @Override
    public double calculateFinalPrice() {
        double exciseTax = this.alcohol < 15 ? lowTax : highTax;
        return getNettoCost() * (1 + tax) + exciseTax;
    }
}

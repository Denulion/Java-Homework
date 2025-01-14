public class Wine extends Product{
    private double alcohol;
    private static final double tax = 0.21;
    private static final double lowTax = 0.28;
    private static final double highTax = 0.72;

    public Wine(String name, double nettoCost, double alcoholContent) {
        super(name, nettoCost);
        this.alcohol = alcoholContent;
    }

    @Override
    public double calculateFinalPrice() {
        double exciseTax = alcohol < 8.5 ? lowTax : highTax;
        return getNettoCost() * (1 + tax) + exciseTax;
    }
}

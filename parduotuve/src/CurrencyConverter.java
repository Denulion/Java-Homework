public class CurrencyConverter {
    private final double conversion = 1.05;

    public double convertToCHF(double euros) {
        return euros * this.conversion;
    }
}

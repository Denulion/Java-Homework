public class CurrencyConverter {
    private static final double conversion = 1.05;

    public static double convertToCHF(double euros) {
        return euros * conversion;
    }
}

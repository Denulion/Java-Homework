import lt.itakademija.exam.*;

public class CurrencyConverterImpl implements CurrencyConverter {
    private CurrencyRatesProvider currencyRatesProvider;

    public CurrencyConverterImpl(CurrencyRatesProvider currencyRatesProvider) {
        this.currencyRatesProvider = currencyRatesProvider;
    }

    @Override
    public Money convert(Currency currency, Currency currency1, Money money) {

        Money rate = this.currencyRatesProvider.getRate(currency, currency1);

        if (rate == null) {
            throw new CurrencyConversionException("Currency exchange rate not found!!!!!");
        }

        return money.multiply(rate);
    }
}

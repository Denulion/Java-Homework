package BeerFactory;

import itadesign.beerdrinker.BeerFactory;
import itadesign.beerdrinker.DefaultBeerBottle;
import itadesign.beerdrinker.NoMoreMaltException;

import java.math.BigDecimal;
import java.util.Random;

public class BalticFactory implements BeerFactory {
    private BigDecimal maltReserves;

    public BalticFactory() {
        this.maltReserves = BigDecimal.valueOf(1.0);
    }

    @Override
    public void receiveMaltShippment(double v) {
        BigDecimal i = new BigDecimal(Double.toString(v));
        this.maltReserves = this.maltReserves.add(i);
    }

    @Override
    public DefaultBeerBottle produceNextBeerBottle() throws NoMoreMaltException {
        BigDecimal i = new BigDecimal("0.025");
        if (maltReserves.compareTo(i) < 0) {
            throw new NoMoreMaltException("There is not enough malt to produce beer!");
        }
        Random random = new Random();

        double volume = new double[]{0.33, 0.5}[random.nextInt(2)];
        double alcohol = 4.5 + (5.7 - 4.5) * random.nextDouble();
        this.maltReserves = this.maltReserves.subtract(i);

        return new DefaultBeerBottle(volume, alcohol);
    }
}

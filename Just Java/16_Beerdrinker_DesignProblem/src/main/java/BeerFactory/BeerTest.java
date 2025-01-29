package BeerFactory;

import itadesign.beerdrinker.BeerConsumer;
import itadesign.beerdrinker.BeerFactory;
import itadesign.test.AbstractBeerTest;

public class BeerTest extends AbstractBeerTest {
    @Override
    protected BeerFactory getBeerFactory() {
        return new BalticFactory();
    }

    @Override
    protected BeerConsumer getBeerConsumer() {
        return new Henryk();
    }
}

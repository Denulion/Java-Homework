package BeerFactory;

import itadesign.beerdrinker.BeerBottle;

public class WrappedBottle implements BeerBottle {
    private BeerBottle wrapperBottle;

    public WrappedBottle(BeerBottle wrapperBottle) {
        this.wrapperBottle = wrapperBottle;
    }

    @Override
    public double getAlcoholContentInPercent() {
        return 0;
    }

    @Override
    public double getVolumeInLiters() {
        return wrapperBottle.getVolumeInLiters();
    }

    @Override
    public void takeASip(double v) {
        this.wrapperBottle.takeASip(v);
    }
}

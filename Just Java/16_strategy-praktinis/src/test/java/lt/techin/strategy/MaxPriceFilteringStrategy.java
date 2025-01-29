package lt.techin.strategy;

public class MaxPriceFilteringStrategy implements FilteringStrategy {
    private final double maxPrice;

    public MaxPriceFilteringStrategy(double maxPrice){
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean filter(Product product) {
        return product.getPrice() <= maxPrice;
    }
}

package lt.techin.strategy;

public class ManufacturerFilteringStrategy implements FilteringStrategy {
    private final String manufacturer;

    public ManufacturerFilteringStrategy(String manufacturer) {
        this.manufacturer = manufacturer;
    }
    @Override
    public boolean filter(Product product) {
        return product.getManufacturer().equalsIgnoreCase(this.manufacturer);
    }
}

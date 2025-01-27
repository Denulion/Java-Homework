package lt.techin.strategy;

public class WarehouseTests extends BaseWarehouseTests{
    @Override
    Warehouse getWarehouse() {
        return new WarehouseTest();
    }

    @Override
    FilteringStrategy getManufacturerFilteringStrategy(String manufacturer) {
        return new ManufacturerFilteringStrategy(manufacturer);
    }

    @Override
    FilteringStrategy getMaxPriceFilteringStrategy(double maxPrice) {
        return new MaxPriceFilteringStrategy(maxPrice);
    }
}

package lt.techin.strategy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

public abstract class BaseWarehouseTests {
    private Warehouse shopStock;
    private List<Product> products;


    abstract Warehouse getWarehouse();

    abstract FilteringStrategy getManufacturerFilteringStrategy(String manufacturer);

    abstract FilteringStrategy getMaxPriceFilteringStrategy(double maxPrice);


    @BeforeEach
    void setUp() {
        products = Arrays.asList(
            new Product("iPhone", 999, "Apple"),
            new Product("Galaxy", 899, "Samsung"),
            new Product("Redmi", 199, "Xiaomi"),
            new Product("iPad", 1299, "APPLE")
        );
        shopStock = getWarehouse();
        shopStock.addProducts(products);
    }

    @Test
    final void testAddProducts() {
        assertEquals(4, shopStock.getNumberOfProducts());
    }


    @Test
    final void testExecuteFilteringStrategyWithManufactureFilter() {
        FilteringStrategy strategy = getManufacturerFilteringStrategy("Apple");
        List<Product> filteredProducts = shopStock.executeFilteringStrategy(strategy);
        assertTrue(filteredProducts.stream().allMatch(p -> p.getManufacturer().equalsIgnoreCase("Apple")));
        assertEquals(2, filteredProducts.size());
    }

    @Test
    final void testExecuteFilteringStrategyWithMaxPriceFilter() {
        FilteringStrategy strategy = getMaxPriceFilteringStrategy(500);
        List<Product> filteredProducts = shopStock.executeFilteringStrategy(strategy);
        assertTrue(filteredProducts.stream().allMatch(p -> p.getPrice() <= 500));
        assertEquals(1, filteredProducts.size());
    }
}

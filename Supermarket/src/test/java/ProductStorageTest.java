import exceptions.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductStorageTest {
    private SupermarketServiceImpl service;

    @BeforeEach
    void setUp(){
        service = SupermarketServiceImpl.getInstance();

        service.storage = new ProductStorage();

        service.storage.addProduct("APPLE", new Product("APPLE", 1.0, 10));
        service.storage.addProduct("BREAD", new Product("BREAD", 2.5, 5));
        service.storage.addProduct("MILK", new Product("MILK", 1.5, 10));
    }

    @Test
    void testAddProduct() {
        service.storage.addProduct("BEER", new Product("BEER", 2.0, 10));
        assertTrue(service.storage.hasProduct("BEER"));
    }

    @Test
    void testMinusProduct() throws SoldOutException {
        service.storage.getProduct("APPLE").setQuantity(1);
        service.storage.minusProduct("APPLE", 1);
        assertFalse(service.storage.hasProduct("APPLE"));
    }
}

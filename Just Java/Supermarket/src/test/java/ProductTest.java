import exceptions.SoldOutException;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {
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
    void testSetQuantity() throws SoldOutException {
        service.storage.getProduct("APPLE").setQuantity(40);
        assertEquals(40, service.storage.getProduct("APPLE").getQuantity(), "You should be capable to change the quantity of product!");
    }
    @Test
    void testProduct() throws SoldOutException {
        String test = service.storage.getProduct("BREAD").toString();
        assertEquals("BREAD: price: 2.5, quantity: 5", test, "Expected: BREAD: price: 2.5, quantity: 5\n Actual: " + test);
    }
}

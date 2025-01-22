import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exceptions.NotEnoughChangeException;
import exceptions.PayNotAcceptedException;
import exceptions.SoldOutException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SupermarketServiceImplTest {
    private SupermarketServiceImpl service;

    @BeforeEach
    void setUp(){
        service = SupermarketServiceImpl.getInstance();

        service.storage = new ProductStorage();
        service.cashRegister = new CashRegister();

        service.storage.addProduct("APPLE", new Product("APPLE", 1.0, 10));
        service.storage.addProduct("BREAD", new Product("BREAD", 2.5, 5));
        service.storage.addProduct("MILK", new Product("MILK", 1.5, 10));

        service.cashRegister.addCash(0.1, 50);
        service.cashRegister.addCash(0.5, 20);
        service.cashRegister.addCash(1.0, 10);
        service.cashRegister.addCash(2.0, 10);
    }

    @Test
    void testSingletonInstance() {
        SupermarketServiceImpl instance1 = SupermarketServiceImpl.getInstance();
        SupermarketServiceImpl instance2 = SupermarketServiceImpl.getInstance();
        assertSame(instance1, instance2, "Instances should be the same for singleton pattern");
    }

    @Test
    void testInitialization(){
        assertFalse(service.storage.getProducts().isEmpty(), "Storage shouldn't be empty after initialization!");
        assertFalse(service.cashRegister.getCoins().isEmpty(), "Cash Register shouldn't be empty after initialization!");
    }

    @Test
    void testHandleChange() throws NotEnoughChangeException, PayNotAcceptedException, SoldOutException {
        service.handleChange(2, 1.5);

        Map<Double, Integer> cashInventory = service.cashRegister.getCoins();
        assertTrue(cashInventory.get(0.5) < 20, "Money should be retracted from the cash register!");
    }
    @Test
    void testPurchaseWithInvalidPayment() {
        assertThrows(PayNotAcceptedException.class, () -> service.handlePayment(-1), "Should throw exception for invalid payment");
    }
}

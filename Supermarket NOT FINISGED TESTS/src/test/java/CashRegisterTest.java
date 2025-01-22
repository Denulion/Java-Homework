import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CashRegisterTest {
    private SupermarketServiceImpl service;

    @BeforeEach
    void setUp(){
        service = SupermarketServiceImpl.getInstance();

        service.cashRegister = new CashRegister();

        service.cashRegister.addCash(0.1, 50);
        service.cashRegister.addCash(0.5, 20);
        service.cashRegister.addCash(1.0, 10);
        service.cashRegister.addCash(2.0, 10);
    }

    @Test
    void testPrintValues() {
        assertEquals("0.1, 0.5, 1.0, 2.0", service.cashRegister.printValues(), "Expected: 0.1, 0.5, 1.0, 2.0\nActual: " + service.cashRegister.printValues());
    }
    //CHANGE TEST IS IN SupermarketServiceImplTest
}

import exceptions.NotEnoughChangeException;
import exceptions.PayNotAcceptedException;
import exceptions.SoldOutException;

public class Main {
    public static void main(String[] args) throws PayNotAcceptedException, SoldOutException, NotEnoughChangeException {
        SupermarketServiceImpl test = new SupermarketServiceImpl();

        test.greetings();
    }
}

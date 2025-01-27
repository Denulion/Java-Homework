import ibank.Bank;
import ibank.BaseBankTest;
import Bank.BankImpl;

public class BaseBankTestImpl extends BaseBankTest {
    @Override
    protected Bank createBank() {
        return new BankImpl();
    }

}

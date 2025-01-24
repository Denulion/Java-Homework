package Bank;

import ibank.Account;

import java.math.BigDecimal;

public class AccountImpl implements Account {
    private String number;
    private String holderName;
    private BigDecimal balance;

    public AccountImpl(String number, String holderName, BigDecimal balance) {
        this.number = number;
        this.holderName = holderName;
        this.balance = balance;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getHolderName() {
        return this.holderName;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    @Override
    public boolean deposit(BigDecimal bigDecimal) {
        if (bigDecimal.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(bigDecimal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean withdraw(BigDecimal bigDecimal) {
        if (bigDecimal.compareTo(BigDecimal.ZERO) > 0 && this.balance.compareTo(bigDecimal) >= 0) {
            this.balance = this.balance.subtract(bigDecimal);
            return true;
        } else {
            return false;
        }
    }
}

package Bank;

import ibank.Account;

import java.math.BigDecimal;

public class CreditAccount implements Account {
    private final String name;
    private final String number;
    private BigDecimal balance;
    private BigDecimal creditLimit;

    public CreditAccount(String name, String number, BigDecimal creditLimit) {
        this.name = name;
        this.number = number;
        this.balance = BigDecimal.ZERO;
        this.creditLimit = creditLimit;
    }

    @Override
    public String getNumber() {
        return this.number;
    }

    @Override
    public String getHolderName() {
        return this.name;
    }

    @Override
    public BigDecimal getBalance() {
        return this.balance;
    }

    public BigDecimal getCreditLimit() {
        return this.creditLimit;
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
        if (bigDecimal.compareTo(BigDecimal.ZERO) > 0 && this.balance.subtract(bigDecimal).compareTo(creditLimit.negate()) >= 0) {
            this.balance = this.balance.subtract(bigDecimal);
            return true;
        } else {
            return false;
        }
    }
}

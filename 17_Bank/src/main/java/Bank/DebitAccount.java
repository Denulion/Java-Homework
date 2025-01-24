package Bank;

import ibank.Account;

import java.math.BigDecimal;

public class DebitAccount implements Account {
    private String name;
    private String number;
    private BigDecimal balance;

    public DebitAccount(String name, String number){
        this.name = name;
        this.number = number;
        this.balance = BigDecimal.ZERO;
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

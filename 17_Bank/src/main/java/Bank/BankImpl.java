package Bank;

import ibank.Account;
import ibank.Bank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BankImpl implements Bank {
    private List<Account> allAccounts;
    private int numbersID;


    public BankImpl() {
        this.allAccounts = new ArrayList<>();
        this.numbersID = 1;

    }

    @Override
    public int getNumberOfAccounts() {
        return this.allAccounts.size();
    }

    @Override
    public BigDecimal getTotalReserves() {
        return  this.allAccounts.stream()
                .map(Account::getBalance)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        //(sum, balance) -> sum.add(balance)
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return new ArrayList<>(this.allAccounts);
    }

    @Override
    public Account openDebitAccount(String s) {
        for (Account account : this.allAccounts) {
            if (account.getHolderName().equalsIgnoreCase(s)) {
                return null;
            }
        }
        this.numbersID++;
        Account acc = new DebitAccount(s, "15000" + this.numbersID);
        this.allAccounts.add(acc);
        return acc;
    }

    @Override
    public Account openCreditAccount(String s, BigDecimal bigDecimal) {
        for (Account account : this.allAccounts) {
            if (account.getHolderName().equalsIgnoreCase(s)) {
                return null;
            }
        }
        this.numbersID++;
        Account acc = new CreditAccount(s, "15000" + this.numbersID, bigDecimal);
        this.allAccounts.add(acc);
        return acc;
    }

    @Override
    public Account getAccountByHolderName(String s) {
        for (Account account : this.allAccounts) {
            if (account.getHolderName().equals(s)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public Account getAccountByNumber(String s) {
        for (Account account : this.allAccounts) {
            if (account.getNumber().equals(s)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void closeAccount(Account account) {
        this.allAccounts.removeIf(acc -> acc.equals(account));
    }
}

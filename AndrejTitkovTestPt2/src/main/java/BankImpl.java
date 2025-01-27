import lt.itakademija.exam.*;
import lt.itakademija.exam.Account;

import java.util.ArrayList;
import java.util.List;

public class BankImpl implements Bank {
    private SequenceGenerator generatorCustomer = new SequenceGenerator();
    private SequenceGenerator generatorAccount = new SequenceGenerator();
    private SequenceGenerator generatorTransaction = new SequenceGenerator();

//    private Map<Long, Customer> accountOwnerMap;

    private CurrencyConverter converter;
    private List<Customer> customerList;

    public BankImpl(CurrencyConverter converter){
        this.converter = converter;
        this.customerList = new ArrayList<>();
//        this.accountOwnerMap = new HashMap<>();
    }

    @Override
    public Customer createCustomer(PersonCode personCode, PersonName personName) throws CustomerCreateException{
        if (personCode == null || personName == null) {
            throw new NullPointerException("One or both arguments are null!");
        }
        for (Customer customer : customerList) {
            if (personCode.equals(customer.getPersonCode())) {
                throw new CustomerCreateException("Customer with this personal code already exists!");
            }
        }
        Customer newCustomer = new Customer(generatorCustomer.getNext(), personCode, personName);
        this.customerList.add(newCustomer);
        return newCustomer;
    }

    @Override
    public Account createAccount(Customer customer, Currency currency) throws AccountCreateException{
        if (customer == null || currency == null) {
            throw new NullPointerException("One or both arguments are null!");
        }
        if (!this.customerList.contains(customer)) {
            throw new AccountCreateException("This customer doesn't belong to any of existing banks");
        }

        Account newAccount = new Account(generatorAccount.getNext(), customer, currency, new Money("0"));
        //this.accountOwnerMap.put(newAccount.getId(), newAccount.getCustomer());
        customer.addAccount(newAccount);
        return newAccount;
    }

    @Override
    public Operation transferMoney(Account account, Account account1, Money money) {
        if (account == null || account1 == null || money == null) {
            throw new NullPointerException("NULL!!!!!");
        }

        Money zero = new Money(0);
        if (account.getBalance().substract(money).isLessThan(zero)) {
            throw new InsufficientFundsException("Not enough funds!");
        }

        Money convertedMoney = money;
        if (!account.getCurrency().equals(account1.getCurrency())) {
            convertedMoney = converter.convert(account.getCurrency(), account1.getCurrency(), money);
        }

        Operation newOperation = new Operation(generatorTransaction.getNext(), account, account1, money);
        account.setBalance(account.getBalance().substract(money));
        account1.setBalance(account1.getBalance().add(convertedMoney));
        return newOperation;
    }

    @Override
    public Money getBalance(Currency currency) {
        if (currency == null) {
            throw new NullPointerException("Your currency is null!");
        }
        Money totalBalance = new Money(0);

        for (Customer customer : customerList) {
            for (Account account : customer.getAccounts()) {
                Money accountBalance = account.getBalance();

                if (!account.getCurrency().equals(currency)) {
                    accountBalance = this.converter.convert(account.getCurrency(), currency, accountBalance);
                }
                totalBalance = totalBalance.add(accountBalance);
            }
        }
        return totalBalance;
    }
}

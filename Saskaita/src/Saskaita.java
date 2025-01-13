public class Saskaita {
    private final String number;
    private final String owner;
    private double balance;

    public Saskaita(String number, String owner, double initialBalance) {
        this.number = number;
        this.owner = owner;
        this.balance = initialBalance;
    }

    public String getNumber() {
        return number;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }
}

public class Saskaita {
    private final String number;
    private final String owner;
    protected double balance;

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
    public void addBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("No funds");
        }
    }
    public boolean takeBalance(double amount) {
        if (amount > this.balance) {
            System.out.println("No funds");
            return false;
        }
        if (amount > 0) {
            this.balance -= amount;
            return true;
        } else {
            System.out.println("Should be positive");
            return false;
        }
    }
    @Override
    public String toString() {
        return "Saskaita{" +
                "number='" + number + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}

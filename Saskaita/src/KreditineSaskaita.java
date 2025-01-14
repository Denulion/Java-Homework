public class KreditineSaskaita extends Saskaita {
    private final double creditLimit;

    public KreditineSaskaita(String number, String owner, double initialBalance, double creditLimit) {
        super(number, owner, initialBalance);
        this.creditLimit = creditLimit;
    }

    @Override
    public boolean takeBalance(double amount) {
        if (amount > (this.balance + this.creditLimit)) {
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
        return "KreditineSaskaita{" +
                "number='" + getNumber() + '\'' +
                ", owner='" + getOwner() + '\'' +
                ", balance=" + this.balance +
                ", creditLimit=" + this.creditLimit +
                '}';
    }
}
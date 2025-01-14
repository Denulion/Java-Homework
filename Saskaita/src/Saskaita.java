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

    public void addBalance(double money){
        if(money > 0.0) this.balance += money;
    }
    public void takeFromBalance(double money){
        if(this.balance - money < 0){

        }
    }
}

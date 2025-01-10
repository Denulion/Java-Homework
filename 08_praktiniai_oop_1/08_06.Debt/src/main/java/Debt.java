public class Debt {
    private double balance;
    private double interestRate;

    public Debt(double balance, double interestRate){
        this.balance = balance;
        this.interestRate = interestRate;
    }
    public void printBalance(){
        System.out.println(balance);
    }
    public void setBalance(double balance){
        this.balance = balance;
    }
    public void waitOneYear(){
        setBalance(balance * interestRate);
    }
}

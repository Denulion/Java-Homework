public class PaymentCard {
    private double balance;

    public PaymentCard(double balance){
        this.balance = balance;
    }
    public void eatAffordably(){
        if((balance - 2.6) >= 0) balance -= 2.6;
    }
    public void eatHeartily(){
        if((balance - 4.6) >= 0) balance -= 4.6;
    }
    public void addMoney(double amount){
        if(amount > 0){
            balance += amount;
            if(balance > 150) balance = 150;
        }
    }
    public String toString(){
        return "The card has a balance of " + balance + " euros";
    }
}

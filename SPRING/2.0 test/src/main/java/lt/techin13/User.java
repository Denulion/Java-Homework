package lt.techin13;

public class User {
    private String name;
    private String personalCode;
    private int balance;

    public User(String name, String personalCode, int balance) {
        this.name = name;
        this.personalCode = personalCode;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(String personalCode) {
        this.personalCode = personalCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}

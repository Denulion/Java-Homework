package lt.techin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Should not be null or empty!")
    @Length(min = 2, max = 80, message = "First name is too long or too short!")
    private String firstName;

    @NotBlank(message = "Should not be null or empty!")
    @Length(min = 2, max = 80, message = "Last name is too long or too short!")
    private String lastName;

    @NotBlank(message = "Should not be null or empty!")
    //@Email
    private String email;

    @NotBlank(message = "Should not be null or empty!")
    @Length(min = 2, max = 20, message = "Phone number is too short or too long!")
    private String phoneNumber;

    @CreationTimestamp
    private LocalDateTime creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public User(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public User(){}

    public long getId() {return id;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public LocalDateTime getCreationDate() {return creationDate;}

    public List<Account> getAccounts() {return accounts;}

    public void setAccounts(List<Account> accounts) {this.accounts = accounts;}

    public Account getAccountByBank(Bank bank) {
        return accounts.stream()
                .filter(account -> account.getBank().equals(bank))
                .findFirst()
                .orElse(null);
    }

    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setUser(null);
    }
}

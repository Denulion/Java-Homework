package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;
import lt.techin.model.Bank;
import org.hibernate.validator.constraints.Length;

public record BankDTO(@NotBlank(message = "Should not be null or empty!")
                      @Length(min = 2, max = 100, message = "Bank name is too long or too short!")
                      String name,
                      @NotBlank(message = "Should not be null or empty!")
                      @Length(min = 10, max = 12, message = "BIC is too long or too short!")
                      String bic,
                      int accountsCount) {

    public BankDTO(Bank bank){
        this(bank.getName(), bank.getBic(), bank.getAccounts().size());
    }

}

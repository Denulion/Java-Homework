package lt.techin.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lt.techin.model.Bank;
import lt.techin.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AccountDTO(@NotBlank(message = "Should not be null or empty!")
                         @Size(min = 10, max = 20, message = "Account number is too long or too short!")
                         String accountNumber,
                         @DecimalMin(value = "0.00", message = "Balance must be at least 0.00!")
                         @Digits(integer = 10, fraction = 2, message = "Invalid balance format!")
                         BigDecimal balance,
                         LocalDateTime creationDate,
                         User user,
                         Bank bank) {
}
package lt.techin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserDTO(@NotBlank(message = "Should not be null or empty!")
                      @Length(min = 2, max = 80, message = "First name is too long or too short!")
                      String firstName,
                      @NotBlank(message = "Should not be null or empty!")
                      @Length(min = 2, max = 80, message = "Last name is too long or too short!")
                      String lastName,
                      @NotBlank(message = "Should not be null or empty!")
                      @Email
                      String email,
                      @NotBlank(message = "Should not be null or empty!")
                      @Length(min = 2, max = 20, message = "Phone number is too short or too long!")
                      String phoneNumber) {
}

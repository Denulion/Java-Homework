package lt.techin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ActorDTO(long id,
                       @NotBlank(message = "Must not be null or empty!")
                       @Size(min = 2, max = 150, message = "Director's name is too short or too long!")
                       @Pattern(regexp = "^[A-Z][^0-9]*$", message = "Actor's name should begin with the capital letter and should not contain any numbers!")
                       String name) {
}

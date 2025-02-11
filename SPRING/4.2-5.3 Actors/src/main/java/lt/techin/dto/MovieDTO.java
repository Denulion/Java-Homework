package lt.techin.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lt.techin.model.Actor;
import lt.techin.model.Screening;

import java.util.List;

public record MovieDTO(long id,
                       @NotBlank(message = "Must not be null or empty!")
                       @Size(min = 2, max = 150, message = "Title is too short or too long!")
                       @Pattern(regexp = "^[A-Z].*$", message = "Title should begin with a capital letter!")
                       String title,
                       @NotBlank(message = "Must not be null or empty!")
                       @Size(min = 2, max = 150, message = "Director's name is too short or too long!")
                       @Pattern(regexp = "^[A-Z][^0-9]*$", message = "Director's name should not contain any letters!")
                       String director,
                       @NotEmpty(message = "List of screenings is empty!")
                       @Valid
                       List<ScreeningDTO> screenings,
                       @NotEmpty(message = "List of actors is empty!")
                       List<ActorDTO> actors) {
}

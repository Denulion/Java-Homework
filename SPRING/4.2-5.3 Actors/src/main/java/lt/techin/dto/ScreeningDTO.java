package lt.techin.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.validation.ValidTime;

import java.time.LocalDate;
import java.time.LocalTime;

public record ScreeningDTO(@NotBlank(message = "Must not be null or empty!")
                           @Size(min = 2, max = 150, message = "Title is too short or too long!")
                           @Pattern(regexp = "^[A-Z].*$", message = "Theatre name should begin with a capital letter!")
                           String screeningTheatre,
                           @ValidTime(message = "Time should be in the future!")
                           LocalTime time,
                           @FutureOrPresent(message = "Date should be today or in the future!")
                           LocalDate date) {
}

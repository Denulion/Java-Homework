package lt.techin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lt.techin.validation.ValidTime;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Must not be null or empty!")
    @Size(min = 2, max = 150, message = "Title is too short or too long!")
    @Pattern(regexp = "^[A-Z].*$", message = "Theatre name should begin with a capital letter!")
    private String screeningTheatre;

    @ValidTime(message = "Time should be in the future!")
    private LocalTime time;

    @FutureOrPresent(message = "Date should be today or in the future!")
    private LocalDate date;

    public Screening(String screeningTheatre, LocalTime time, LocalDate date) {
        this.screeningTheatre = screeningTheatre.trim();
        this.time = time;
        this.date = date;
    }

    public Screening() {
    }

    public long getId() {
        return id;
    }

    public String getScreeningTheatre() {
        return screeningTheatre;
    }

    public void setScreeningTheatre(String screeningTheatre) {
        this.screeningTheatre = screeningTheatre.trim();
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

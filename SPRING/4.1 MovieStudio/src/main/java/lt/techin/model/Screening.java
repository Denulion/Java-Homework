package lt.techin.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "screenings")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String screeningTheatre;
    private LocalTime time;
    private LocalDate date;

    public Screening(String screeningTheatre, LocalTime time, LocalDate date) {
        this.screeningTheatre = screeningTheatre;
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
        this.screeningTheatre = screeningTheatre;
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

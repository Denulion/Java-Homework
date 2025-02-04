package lt.techin.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Must not be null or empty!")
    @Size(min = 2, max = 150, message = "Title is too short or too long!")
    @Pattern(regexp = "^[A-Z].*$", message = "Title should begin with a capital letter!")
    private String title;

    @NotBlank(message = "Must not be null or empty!")
    @Size(min = 2, max = 150, message = "Director's name is too short or too long!")
    @Pattern(regexp = "^[A-Z][^0-9]*$", message = "Director's name should not contain any letters!")
    private String director;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    @NotEmpty(message = "List of screenings is empty!")
    @Valid
    private List<Screening> screenings;

    @ManyToMany
    @JoinTable(
            name = "movies_actors",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id")
    )
    @NotEmpty(message = "List of actors is empty!")
    private List<Actor> actors;

    public Movie(String title, String director, List<Screening> screenings, List<Actor> actors) {
        this.title = title.trim();
        this.director = director.trim();
        this.screenings = screenings;
        this.actors = actors;
    }

    public Movie() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.trim();
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director.trim();
    }

    public List<Screening> getScreenings() {
        return screenings;
    }

    public void setScreenings(List<Screening> screenings) {
        this.screenings = screenings;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}

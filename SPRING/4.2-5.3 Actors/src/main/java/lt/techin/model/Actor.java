package lt.techin.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Must not be null or empty!")
    @Size(min = 2, max = 150, message = "Director's name is too short or too long!")
    @Pattern(regexp = "^[A-Z][^0-9]*$", message = "Actor's name should begin with the capital letter and should not contain any numbers!")
    private String name;

    public Actor(String name) {
        this.name = name.trim();
    }

    public Actor() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.trim();
    }
}

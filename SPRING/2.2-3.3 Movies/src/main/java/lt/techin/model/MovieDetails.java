package lt.techin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "movies_details")
public class MovieDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String isbn;
    private int lengthInMinutes;
    private String language;

    public MovieDetails(String isbn, int lengthInMinutes, String language) {
        this.isbn = isbn;
        this.lengthInMinutes = lengthInMinutes;
        this.language = language;
    }

    public MovieDetails() {
    }

    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

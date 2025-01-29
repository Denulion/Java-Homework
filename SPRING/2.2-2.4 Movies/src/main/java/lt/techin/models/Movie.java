package lt.techin.models;

public class Movie {
    private final int id;
    private final String title;
    private final String director;

    public Movie(int id, String title, String director) {
        this.id = id;
        this.title = title;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }
}

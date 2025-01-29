import java.util.Objects;

public class Book {

    private String name;
    private int publicationYear;

    public Book(String name, int publicationYear) {
        this.name = name;
        this.publicationYear = publicationYear;
    }

    public String getName() {
        return name;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        Book book = (Book) obj;
        return publicationYear == book.publicationYear && Objects.equals(name, book.name);
    }
}

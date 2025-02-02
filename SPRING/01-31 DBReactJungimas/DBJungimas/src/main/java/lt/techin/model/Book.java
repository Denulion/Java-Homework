package lt.techin.model;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String author;
    private String category;
    private double price;
    private String cover;
    private boolean reserved;

    public Book(String title, String author, String category, double price, String cover, boolean reserved) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.cover = cover;
        this.reserved = reserved;
    }

    public Book() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getCover() {
        return cover;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}

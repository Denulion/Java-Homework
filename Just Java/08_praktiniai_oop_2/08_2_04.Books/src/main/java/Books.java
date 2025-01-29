public class Books {
    private String title;
    private int pages;
    private int publicationYear;

    public Books(String title, int pages, int publicationYear){
        this.title = title;
        this.pages = pages;
        this.publicationYear = publicationYear;
    }
    public String getTitle(){
        return title;
    }
    public int getPages(){
        return pages;
    }
    public int getPublicationYear(){
        return publicationYear;
    }
    public String toString(){
        return this.title + ", " + this.pages + ", " + this.publicationYear;
    }
}

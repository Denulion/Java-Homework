import java.util.Objects;

public class Book implements Comparable<Book>{
    private String name;
    private final int ageRecommended;

    public Book(String name, int ageRecommended){
        this.name = name;
        this.ageRecommended = ageRecommended;
    }

    public String getName() {
        return name;
    }

    public int getAgeRecommended() {
        return ageRecommended;
    }

    public String toString(){

        return this.name + " (recommended for " + this.ageRecommended + " years-olds or older)";
    }
    @Override
    public int compareTo(Book other) {
        if(this.ageRecommended == other.getAgeRecommended()){
            return this.name.compareToIgnoreCase(other.getName());
        }
        return Integer.compare(this.ageRecommended, other.getAgeRecommended());
    }
}

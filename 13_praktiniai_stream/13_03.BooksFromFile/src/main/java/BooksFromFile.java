
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BooksFromFile {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // test your method here

    }
    public static List<Book> readBooks(String file){
        try {
            return Files.lines(Paths.get(file)).map(i -> {
                String[] parts = i.split(",");
                String name = parts[0];
                int year = Integer.parseInt(parts[1].trim());
                int pages = Integer.parseInt(parts[2].trim());
                String author = parts[3];
                return new Book(name, year, pages, author);
            }).collect(Collectors.toList());
        } catch (IOException e){
            System.err.println("Error: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}

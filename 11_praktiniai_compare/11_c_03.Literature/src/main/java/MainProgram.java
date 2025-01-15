
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Input the name of the book, empty stops: ");
            String nameInput = scanner.nextLine();

            if(nameInput.isEmpty()) break;

            System.out.println("Input the age recommendation: ");
            int ageRecommendationInput = Integer.parseInt(scanner.nextLine());

            bookList.add(new Book(nameInput, ageRecommendationInput));
        }

        System.out.println(bookList.size() + " books in total.");

        Collections.sort(bookList);
        System.out.println("Books:");
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

}

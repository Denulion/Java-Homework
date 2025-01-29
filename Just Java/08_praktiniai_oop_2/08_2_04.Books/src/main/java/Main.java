import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        ArrayList<Books> bookCollection = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String theTitle = scanner.nextLine();
            if(theTitle.isEmpty()) break;
            int pageCount = Integer.parseInt(scanner.nextLine());
            int releaseYear = Integer.parseInt(scanner.nextLine());

            Books book = new Books(theTitle, pageCount, releaseYear);
            bookCollection.add(book);
        }

        System.out.println("What information will be printed? ");
        String input = scanner.nextLine();
        for(Books oneBook : bookCollection){
            if(input.equals("name")) {
                System.out.println(oneBook.getTitle());
            } else if(input.equals("everything")) {
                System.out.println(oneBook.getTitle() + ", " + oneBook.getPages() + " pages, " + oneBook.getPublicationYear());
            }
        }
    }
}

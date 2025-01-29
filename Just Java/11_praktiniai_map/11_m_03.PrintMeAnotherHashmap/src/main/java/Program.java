
import java.util.HashMap;

public class Program {

    public static void main(String[] args) {
        HashMap<String, Book> hashmap = new HashMap<>();
        hashmap.put("sense", new Book("Sense and Sensibility", 1811, "..."));
        hashmap.put("prejudice", new Book("Pride and Prejudice", 1813, "...."));

        printValues(hashmap);
        System.out.println("---");
        printValueIfNameContains(hashmap, "prejud");


    }
    public static void printValues(HashMap<String, Book> hashMap){
        for (String key : hashMap.keySet()){
            System.out.println(hashMap.get(key));
        }
    }
    public static void printValueIfNameContains(HashMap<String, Book> hashMap, String text){
        for(Book book : hashMap.values()){
            if(book.getName().toLowerCase().contains(text)){
                System.out.println(book);
            }
        }
    }
}

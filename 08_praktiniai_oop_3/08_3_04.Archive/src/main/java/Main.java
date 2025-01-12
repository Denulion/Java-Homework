
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Archive> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("Identifier? (empty will stop)");
            String identifier = scanner.nextLine();
            if(identifier.isEmpty()) break;

            System.out.println("Name? (empty will stop)");
            String name = scanner.nextLine();
            if(name.isEmpty()) break;

            Archive test = new Archive(identifier, name);
            if(list.contains(test)) {
                System.out.println("UwU");
                continue;
            }

            list.add(test);
        }
        System.out.println("==Items==");
        for (Archive toy : list) {
            System.out.println(toy.toString());
        }
    }
}


import java.util.Scanner;

public class MainProgram {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String test1 = scanner.nextLine();
        int test2 = Integer.parseInt(scanner.nextLine());
        Room r1 = new Room(test1, test2);
        System.out.println(r1);
        r1.setSeats(test2 + 4);
        System.out.println(r1);

        System.out.println("The code is: " + r1.getCode());

    }

}


import java.util.Scanner;

public class GiftTax {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Value of the gift?");
        int input = Integer.parseInt(scan.nextLine());
        if (input < 5000) {
            System.out.println("No tax!");
        } else if (5000 <= input && input < 25000) {
            System.out.println("Tax: " + (100 + (input - 5000) * 0.08));
        } else if (25000 <= input && input < 55000) {
            System.out.println("Tax: " + (1700 + (input - 25000) * 0.1));
        } else if (55000 <= input && input < 200_000) {
            System.out.println("Tax: " + (4700 + (input - 55000) * 0.12));
        } else if (200_000 <= input && input < 1_000_000) {
            System.out.println("Tax: " + (22100 + (input - 200_000) * 0.15));
        }else {
            System.out.println("Tax: " + (142_100 + (input - 1_000_000) * 0.17));
        }
    }
}

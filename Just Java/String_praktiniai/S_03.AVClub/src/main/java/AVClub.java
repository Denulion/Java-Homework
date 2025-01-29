
import java.util.Scanner;

public class AVClub {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true){
            String input = scanner.nextLine();

            if(input.isEmpty()) break;

            String[] splitted = input.split(" ");
            for (String s : splitted) {
                if (s.contains("av")) System.out.println(s);
            }
        }
    }
}

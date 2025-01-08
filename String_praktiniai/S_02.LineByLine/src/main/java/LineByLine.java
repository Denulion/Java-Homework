
import java.util.Scanner;

public class LineByLine {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if(input.isEmpty()) break;
            String[] splitLength = input.split(" ");
            for (String s : splitLength) {
                System.out.println(s);
            }
        }
        //System.out.println();
    }
}

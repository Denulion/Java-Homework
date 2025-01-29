
import java.util.Scanner;

public class NameOfTheOldest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daBiggestNumbah = 0;
        String theName = "";
        while(true){
            String input = scanner.nextLine();
            if(input.isEmpty()) break;

            String[] splitter = input.split(",");

            int num = Integer.parseInt(splitter[1]);

            if (num > daBiggestNumbah) {
                theName = splitter[0];
                daBiggestNumbah = num;
            }
        }
        System.out.println("Name of the oldest: " + theName);
    }
}

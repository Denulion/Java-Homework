
import java.util.Scanner;

public class AgeOfTheOldest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daBiggestNumbah = 0;
        while(true){
            String input = scanner.nextLine();
            if(input.isEmpty()) break;

            String[] splitter = input.split(",");

            int num = Integer.parseInt(splitter[1]);

            if (num > daBiggestNumbah) daBiggestNumbah = num;
        }
        System.out.println("Age of the oldest: " + daBiggestNumbah);
    }
}

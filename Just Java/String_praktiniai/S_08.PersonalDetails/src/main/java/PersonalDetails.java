
import java.util.ArrayList;
import java.util.Scanner;

public class PersonalDetails {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int years = 0;
        double countOfPeople = 0;
        String theName = "";
        while(true){
            String input = scanner.nextLine();
            if(input.isEmpty()) break;

            String[] splitter = input.split(",");

            int num = Integer.parseInt(splitter[1]);
            years += num;
            countOfPeople += 1.0;
            if(theName.length() < splitter[0].length()) theName = splitter[0];
        }
        System.out.println("Longest name: " + theName);
        System.out.println("Average of the birth years: " + (years / countOfPeople));
    }
}

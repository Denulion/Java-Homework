
import java.util.ArrayList;
import java.util.Scanner;

public class IndexOfSmallest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Integer> list = new ArrayList<>();
        while(true){
            int input = Integer.parseInt(scanner.nextLine());
            if (input == 9999) {
                break;
            }
            list.add(input);
        }
        System.out.println();

        int smallest = list.get(0);
        for (Integer num : list) {
            if (num < smallest) {
                smallest = num;
            }
        }
        System.out.println("Smallest number: " + smallest);

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == smallest) System.out.println("Found at index: " + i);
        }
    }
}

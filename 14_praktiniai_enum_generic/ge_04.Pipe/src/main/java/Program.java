
import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pipe<Integer> numberPipe = new Pipe<>();
        numberPipe.putIntoPipe(2);
        numberPipe.putIntoPipe(4);
        numberPipe.putIntoPipe(12);

        int sum = 0;
        while(numberPipe.isInPipe()){
            sum += numberPipe.takeFromPipe();
        }
        System.out.println(sum);
        System.out.println(numberPipe.takeFromPipe());
    }
}

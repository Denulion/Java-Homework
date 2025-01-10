import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // implement here your program that uses the TelevisionProgram class

        ArrayList<TelevisionProgram> programs = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while(true){
            String name = scanner.nextLine();
            if(name.isEmpty()) break;
            int duration = Integer.parseInt(scanner.nextLine());

            TelevisionProgram program = new TelevisionProgram(name, duration);
            programs.add(program);
        }

        int input = Integer.parseInt(scanner.nextLine());
        for (TelevisionProgram prog : programs){
            if(prog.getDuration() <= input) {
                System.out.println(prog.getName() + " " + prog.getDuration());
            }
        }
    }
}

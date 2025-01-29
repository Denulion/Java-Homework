
import java.util.Scanner;

public class mainProgram {

    public static void main(String[] args) {
        // NB! Do not create other scanner objects than the one below
        // if and when you create other classes, pass the scanner to them
        // as a parameter

        Scanner scan = new Scanner(System.in);
        BirdDatabase birdDatabase = new BirdDatabase();

        while (true) {
            System.out.print("? ");
            String command = scan.nextLine();

            try {
                if (command.equalsIgnoreCase("Add")) {
                    System.out.print("Name: ");
                    String name = scan.nextLine();
                    System.out.print("Name in Latin: ");
                    String latinName = scan.nextLine();
                    birdDatabase.addBird(name, latinName);

                } else if (command.equalsIgnoreCase("Observation")) {
                    System.out.print("Bird? ");
                    String name = scan.nextLine();
                    birdDatabase.addObservation(name);

                } else if (command.equalsIgnoreCase("All")) {
                    birdDatabase.printAll();

                } else if (command.equalsIgnoreCase("One")) {
                    System.out.print("Bird? ");
                    String name = scan.nextLine();
                    birdDatabase.printOne(name);

                } else if (command.equalsIgnoreCase("Quit")) {
                    break;

                } else {
                    System.out.println("Unknown command!");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }

        }
    }

}

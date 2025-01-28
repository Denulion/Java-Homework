import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("Yes or no?");
      String input = scanner.nextLine();
      if (input.equalsIgnoreCase("Yes")) {
        continue;
      } else if (input.equalsIgnoreCase("No")) {
        break;
      } else {
        System.out.println("Wrong input!!!!!");
      }
    }
  }

}

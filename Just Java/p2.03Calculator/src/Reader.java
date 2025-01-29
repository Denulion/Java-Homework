import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader() {
        this.scanner = new Scanner(System.in);
    }

    public String readString() {
        return this.scanner.nextLine();
    }

    public int readInteger() {
        return Integer.parseInt(this.scanner.nextLine());
    }
}
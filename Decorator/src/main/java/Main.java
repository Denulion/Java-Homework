public class Main {
    public static void main(String[] args) {

        Greeter greeter = new GreeterImpl();

        greeter = new GreeterUpperCaseDecorator(new GreeterExclamationMarkDecorator(greeter));

        System.out.println(greeter.greet("Jonas", "Jonaitis"));
    }
}

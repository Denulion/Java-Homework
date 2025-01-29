public class GreeterExclamationMarkDecorator implements Greeter{
    private Greeter greeter;

    public GreeterExclamationMarkDecorator(Greeter greeter) {
        this.greeter = greeter;
    }
    @Override
    public String greet(String firstName, String lastName) {
        return greeter.greet(firstName, lastName) + "!";
    }
}

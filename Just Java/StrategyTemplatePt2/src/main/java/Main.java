public class Main {
    public static void main(String[] args) {

        Greeter labas = new Greeter(new LithuanianGreetingStrategy(), new Dot());

        labas.greet("Jonas", "Jonaitis");

        labas.setGreeter(new EnglishGreetingStrategy());
        labas.setEnding(new ThreeExclamationMarks());

        labas.greet("Jonas", "Jonaitis");
    }
}

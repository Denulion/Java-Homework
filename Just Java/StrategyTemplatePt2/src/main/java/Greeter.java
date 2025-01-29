public class Greeter {
    GreeterLanguageStrategy greeterLanguageStrategy;
    EndingStrategy endingStrategy;

    public Greeter(GreeterLanguageStrategy greeterLanguageStrategy, EndingStrategy endingStrategy) {
        this.greeterLanguageStrategy = greeterLanguageStrategy;
        this.endingStrategy = endingStrategy;
    }

    public void setGreeter(GreeterLanguageStrategy gls){
        this.greeterLanguageStrategy = gls;
    }

    public void greet(String firstName, String lastName) {
        System.out.println(this.greeterLanguageStrategy.getGreetingString() + " " + firstName + " " + lastName + this.endingStrategy.getEndingStrategy());
    }

    public void setEnding(EndingStrategy endingStrategy) {
        this.endingStrategy = endingStrategy;
    }
}

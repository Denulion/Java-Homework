package lt.techin.oop;

public class Animal {
    private String color;
    private int numberOfPaws;
    private boolean hasFur;

    public Animal(String color, int numberOfPaws, boolean hasFur) {
        this.color = color;
        this.numberOfPaws = numberOfPaws;
        this.hasFur = hasFur;
    }

    public String getDescription() {
        return "This animal is mostly " + this.color + ". It has "
                + this.numberOfPaws + " paws and " + (this.hasFur ? "a" : "no") + " fur.";
    }
}

import java.util.ArrayList;

public class BirdDatabase {
    private final ArrayList<Bird> birdArrayList;

    public BirdDatabase (){
        this.birdArrayList = new ArrayList<>();
    }
    public BirdDatabase(ArrayList<Bird> birdArrayList) {
        if (birdArrayList == null) {
            throw new IllegalArgumentException("Bird list cannot be null!");
        }

        this.birdArrayList = birdArrayList;
    }

    public void addBird(String name, String nameLatin) {
        if (this.birdArrayList.stream().anyMatch(bird -> bird.getName().equalsIgnoreCase(name))) {
            throw new IllegalStateException("Bird already exists: " + name);
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Bird name cannot be null or empty!");
        }
        if (nameLatin == null || nameLatin.trim().isEmpty()) {
            throw new IllegalArgumentException("Bird Latin name cannot be null or empty!");
        }

        Bird addedBird = new Bird(name, nameLatin);
        this.birdArrayList.add(addedBird);
    }

    public void addObservation(String name) {
        for (Bird bird : this.birdArrayList) {
            if (bird.getName().equalsIgnoreCase(name)) {
                bird.addObservations();
                return;
            }
        }
        System.out.println("Not a bird!");
    }

    public void printAll() {
        this.birdArrayList.forEach(System.out::println);
    }

    public void printOne(String name) {
        for (Bird bird : this.birdArrayList) {
            if (bird.getName().equalsIgnoreCase(name)) {
                System.out.println(bird);
                return;
            }
        }
        System.out.println("Not a bird!");
    }
}

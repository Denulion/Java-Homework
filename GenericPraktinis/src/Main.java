import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("test", "test2", "test3");
        String lastWord = ElementUtils.lastElement(stringList);
        System.out.println("Last word: " + lastWord);

        String[] wordArray = {"test", "test2", "test3"};
        String lastWordArray = ElementUtils.lastElement(wordArray);
        System.out.println("Last word in array: " + lastWordArray);

        Box<Integer> nums = new Box<>(8, 5);
        System.out.println(nums);
        System.out.println("First item: " + nums.getItem1());
        System.out.println("Second item: " + nums.getItem2());

        nums.lock();
        System.out.println(nums.isLocked());
        try {
            System.out.println("Locked box: " + nums.getItem1());
        } catch (IllegalStateException e){
            System.out.println("Exception: " + e.getMessage());
        }
        nums.unlock();
        System.out.println("Unlocked box: " + nums.getItem1());

        System.out.println(nums.isLocked());
    }

}

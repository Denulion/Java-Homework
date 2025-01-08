
public class Main {
    public static void main(String[] args) {
        int[] array = {4,2,3,4,5,16,6};
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        System.out.println("smallest: " + min);
    }

}

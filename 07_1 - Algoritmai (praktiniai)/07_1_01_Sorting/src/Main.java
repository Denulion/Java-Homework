import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] array = {-1, 3, 1, 7, 4, 5, 2, 4, 3};

        System.out.println("Smallest: " + smallest(array));
        System.out.println("Index of the smallest: " + indexOfTheSmallest(array));
        System.out.println(indexOfTheSmallestStartingFrom(array, 1));
        System.out.println(indexOfTheSmallestStartingFrom(array, 2));
        System.out.println(indexOfTheSmallestStartingFrom(array, 4));

        int[] values = {8, 3, 7, 9, 1, 2, 4};
        sort(values);

//        swap(values, 1, 0);
//        System.out.println(Arrays.toString(values));
//
//        swap(values, 0, 3);
//        System.out.println(Arrays.toString(values));
    }

    public static int smallest(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    public static int indexOfTheSmallest(int[] arr) {
        int min = smallest(arr);
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            if (min == arr[i]) index = i;
        }
        return index;
    }

    public static int indexOfTheSmallestStartingFrom(int[] arr, int index) {
        int min = arr[index];
        int indexOftheSmallest = index;
        for (int i = index; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
                indexOftheSmallest = i;
            }
        }
        return indexOftheSmallest;
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    public static void sort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int nextIndex = indexOfTheSmallestStartingFrom(arr, i);
            swap(arr, i, nextIndex);
            System.out.println(Arrays.toString(arr));
        }
    }
}

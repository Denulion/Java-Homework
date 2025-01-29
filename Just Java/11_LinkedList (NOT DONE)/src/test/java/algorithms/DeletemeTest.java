package algorithms;

import org.junit.Test;

import java.util.Arrays;

public class DeletemeTest {

    @Test
    public void test() {
        int[] a = new int[] {1,2,3};
        int[] b = new int[3];

        System.arraycopy(a, 0, b, 0, 3);

        System.out.println(Arrays.toString(b));
    }

}

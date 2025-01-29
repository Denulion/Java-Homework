import lt.itakademija.exam.IntegerGenerator;

public class IntegerGeneratorImpl implements IntegerGenerator {
    private int min;
    private int max;
    private int current;

    public IntegerGeneratorImpl(int min, int max) {
        this.min = min;
        this.max = max;
        this.current = 0;
    }

    @Override
    public Integer getNext() {
        int temp = this.min + this.current;
        this.current++;
        if (temp > max) {
            return null;
        }
        return temp;
    }
}

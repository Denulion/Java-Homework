import lt.itakademija.exam.IntegerGenerator;
import lt.itakademija.exam.NumberFilter;

public class FilteredIntegerGenerator implements IntegerGenerator {
    private IntegerGenerator integerGenerator;
    private NumberFilter numberFilter;

    public FilteredIntegerGenerator(IntegerGenerator integerGenerator, NumberFilter numberFilter) {
        this.integerGenerator = integerGenerator;
        this.numberFilter = numberFilter;
    }

    @Override
    public Integer getNext() {
        return 0;
    }
}

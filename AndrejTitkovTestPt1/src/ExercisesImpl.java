import lt.itakademija.exam.IntegerGenerator;
import lt.itakademija.exam.NumberFilter;

import java.util.*;

public class ExercisesImpl implements lt.itakademija.exam.Exercises {
    @Override
    public Integer findSmallest(List<Integer> list) {
        int smallest = list.getFirst();
        for (Integer integer : list) {
            if (smallest > integer) {
                smallest = integer;
            }
        }
        return smallest;
    }

    @Override
    public Integer findLargest(List<Integer> list) {
        int largest = list.getFirst();
        for (Integer integer : list) {
            if (largest < integer) {
                largest = integer;
            }
        }
        return largest;
    }

    @Override
    public boolean isEqual(Object o, Object o1) {
        return o.equals(o1);
    }

    @Override
    public boolean isSameObject(Object o, Object o1) {
        return o == o1;
    }

    @Override
    public List<Integer> consume(Iterator<Integer> iterator) {
        List<Integer> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Integer temp = iterator.next();
            list.add(temp);
        }
        return list;
    }

    @Override
    public int computeSumOfNumbers(int i) {
        int sum = 0;
        for (int j = 1; j <= i; j++) {
            sum += j;
        }
        return sum;
    }

    @Override
    public int computeSumOfNumbers(int i, NumberFilter numberFilter) {
        int sum = 0;
        for (int j = 1; j <= i; j++) {
            if(numberFilter.accept(j)) {
                sum += j;
            }
        }
        return sum;
    }

    @Override
    public List<Integer> computeNumbersUpTo(int i) {
        List<Integer> list = new ArrayList<>();
        for (int j = 1; j < i; j++) {
            list.add(j);
        }
        return list;
    }

    @Override
    public Map<Integer, Integer> countOccurrences(List<Integer> list) {
        Map<Integer, Integer> tempMap = new HashMap<>();
        for (Integer integer : list) {
            tempMap.put(integer, tempMap.getOrDefault(integer, 0) + 1);
        }
        return tempMap;
    }

    @Override
    public IntegerGenerator createIntegerGenerator(int i, int i1) {
        return new IntegerGeneratorImpl(i, i1) {
        };
    }

    @Override
    public IntegerGenerator createFilteredIntegerGenerator(IntegerGenerator integerGenerator, NumberFilter numberFilter) {
        return new FilteredIntegerGenerator(integerGenerator, numberFilter);
    }
}

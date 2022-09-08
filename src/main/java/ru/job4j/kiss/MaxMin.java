package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T sortByComparator(List<T> value, Comparator<T> comparator) {
        T temp = null;
        if (!value.isEmpty()) {
            temp = value.get(0);
            for (T val : value) {
                if (comparator.compare(val, temp) > 0) {
                    temp = val;
                }
            }
        }
        return temp;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        return sortByComparator(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return sortByComparator(value, comparator.reversed());
    }
}

package ru.job4j.kiss;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void whenIntMaxSorted() {
        MaxMin maxMin = new MaxMin();
        List<Integer> numbers = List.of(1, 3, 5, 7);
        int expected = 7;
        int result = maxMin.max(numbers, Integer::compareTo);
        assertThat(expected, is(result));
    }

    @Test
    public void whenIntMinSorted() {
        MaxMin maxMin = new MaxMin();
        List<Integer> numbers = List.of(1, 3, 5, 7);
        int expected = 1;
        int result = maxMin.min(numbers, Integer::compareTo);
        assertThat(expected, is(result));
    }

    @Test
    public void whenNullIsResult() {
        MaxMin maxMin = new MaxMin();
        List<Integer> numbers = List.of();
        assertNull(maxMin.max(numbers, Integer::compareTo));
    }

    @Test
    public void whenStringMaxSorted() {
        MaxMin maxMin = new MaxMin();
        List<String> strings = List.of("AAA", "BBB", "CCC");
        String expected = "CCC";
        assertThat(expected, is(maxMin.max(strings, String::compareTo)));
    }

    @Test
    public void whenStringMinSorted() {
        MaxMin maxMin = new MaxMin();
        List<String> strings = List.of("AAA", "BBB", "CCC");
        String expected = "AAA";
        String result = maxMin.min(strings, String::compareTo);
        assertThat(expected, is(result));
    }
}
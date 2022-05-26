package ru.job4j.iterator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfAbove3() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4));
        Predicate<Integer> pred = x -> x > 2;
        ListUtils.removeIf(input, pred);
        assertThat(input, is(Arrays.asList(0, 1, 2)));
    }

    @Test
    public void whenReplaceIfAbove1With1() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Predicate<Integer> pred = x -> x > 1;
        ListUtils.replaceIf(input, pred, 1);
        assertThat(input, is(Arrays.asList(1, 1, 1, 1)));
    }

    @Test
    public void whenRemoveAllIfTheSameElementsInTheSecondList() {
        List<Integer> firstlist = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> secondlist = new ArrayList<>(Arrays.asList(3, 4));
        ListUtils.removeAll(firstlist, secondlist);
        assertThat(firstlist, is(Arrays.asList(1, 2, 5, 6)));
    }
}
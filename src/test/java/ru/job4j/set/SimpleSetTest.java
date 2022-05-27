package ru.job4j.set;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenIteratorHasNext() {
        Set<Integer> set = new SimpleSet<>();
        set.add(3);
        set.add(4);
        Iterator<Integer> iterator = set.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals(Integer.valueOf(3), iterator.next());
    }

    @Test
    public void whenNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
    }
}
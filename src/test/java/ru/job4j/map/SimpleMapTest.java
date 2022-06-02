package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutAndGetThenTrue() {
        Map<String, String> map = new SimpleMap<>();
        map.put("Hello", "Привет");
        map.put("GoodBye", "Пока");
        assertThat("Привет", is(map.get("Hello")));
    }

    @Test
    public void whenRemoveThenNull() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("TV", 100);
        map.put("Phone", 200);
        map.remove("TV");
        assertNull(map.get("TV"));
    }

    @Test
    public void whenMoreThan8ThenExpand() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        assertThat(8, is(map.get("eight")));
    }

    @Test
    public void whenIterator() {
        Map<String, Integer> map = new SimpleMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        assertThat("zero", is(iterator.next()));
        Assert.assertTrue(iterator.hasNext());
        assertThat("two", is(iterator.next()));
        Assert.assertTrue(iterator.hasNext());
        assertThat("one", is(iterator.next()));

    }

}
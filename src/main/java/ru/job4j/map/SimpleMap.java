package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];


    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        int indexKey = indexFor(hash(key.hashCode()));
        if (table[indexKey] == null) {
            table[indexKey] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int indexKey = indexFor(hash(key.hashCode()));
        if (table[indexKey] != null && Objects.equals(table[indexKey].key, key)) {
            rsl = table[indexKey].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int indexKey = indexFor(hash(key.hashCode()));
        if (table[indexKey] != null && Objects.equals(table[indexKey].key, key)) {
            table[indexKey].key = null;
            table[indexKey].value = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {

        return new Iterator<K>() {

            private int expectedModCount = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (table[index] == null && index < capacity - 1) {
                    index++;
                }
                return index <= capacity - 1;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity];
        count = 0;
        for (MapEntry<K, V> mapEntry : oldTable) {
            if (mapEntry != null) {
                put(mapEntry.key, mapEntry.value);
            }
        }
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}


package ru.job4j.map;

import java.util.*;

public class UserMap {
    public static void main(String[] args) {
        Calendar birthday = new GregorianCalendar(1980, 8, 8);
        User user1 = new User("Tom", 1, birthday);
        User user2 = new User("Tom", 1, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((key, value) -> System.out.println(key + " -> " + value));

        int index1 = (user1.hashCode() ^ (user1.hashCode() >>> 16)) & 15;
        int hash1 = (user1.hashCode() ^ (user1.hashCode() >>> 16));
        System.out.println("user1 index: " + index1);
        System.out.println("user1 hashCode: " + hash1);
        int index2 = (user2.hashCode() ^ (user2.hashCode() >>> 16)) & 15;
        int hash2 = (user2.hashCode() ^ (user2.hashCode() >>> 16));
        System.out.println("user2 index: " + index2);
        System.out.println("user2 hashCode: " + hash2);
    }
}

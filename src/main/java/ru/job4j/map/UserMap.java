package ru.job4j.map;

import java.util.*;

public class UserMap {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar(1980, 8, 8);
        User user1 = new User("Tom", 1, calendar);
        User user2 = new User("Alibaba", 7, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        map.forEach((key, value) -> System.out.println(key + " -> " + value));
    }
}

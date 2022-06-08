package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;

        Map<Integer, User> previousUsers = new HashMap<>();

        for (User user : previous) {
            previousUsers.put(user.getId(), user);
        }
        for (User user : current) {
            User preUser = previousUsers.get(user.getId());
            if (preUser == null) {
                added++;
            } else if (!user.equals(preUser)) {
                changed++;
            }
            previousUsers.remove(user.getId());
        }
        int deleted = previousUsers.size();
        return new Info(added, changed, deleted);

    }
/*    public static Info diff(Set<User> previous, Set<User> current) {

        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();

        previous.forEach(e -> previousMap.put(e.getId(), e.getName()));
        current.forEach(e-> currentMap.put(e.getId(), e.getName()));

        int addedCount = (int) currentMap.keySet().stream()
                .filter(e-> !previousMap.containsKey(e))
                .count();

        int deletedCount = (int) previousMap.keySet().stream()
                .filter(e-> !currentMap.containsKey(e))
                .count();

        int changedCount = (int) previousMap.keySet().stream()
                .filter(currentMap::containsKey)
                .filter(e-> !previousMap.get(e).equals(currentMap.get(e)))
                .count();

        return new Info(deletedCount, changedCount, addedCount);
    }*/

/*    public static Info diff(Set<User> previous, Set<User> current) {
        int change = 0, count =0;
        Map<Integer, String> actual = current.stream()
                .collect(Collectors.toMap(User::getId, User::getName));
        for (User user : previous) {
            if (actual.containsKey(user.getId())
                    && !actual.containsValue(user.getName())) {
                change++;
            } else if (!actual.containsKey(user.getId())) {
                count++;
            }
        }
        return new Info(actual.size() - previous.size() + count, change, count);
    }*/

/*    public static Info diff(Set<User> previous, Set<User> current) {
        int deleted = 0;
        int changed = 0;
        int added = 0;

        Map<Integer, User> map = new HashMap<>();

        for (User user : previous) {
            map.put(user.getId(), user);
        }

        for (User user : current) {
            User temp = map.putIfAbsent(user.getId(), user);
            if (temp !=null) {
                if(!user.equals(temp)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }*/

/*    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        Map<Integer, String> currentMap = new HashMap<>();
        Map<Integer, String> previousMap = new HashMap<>();

        for (User cUser : current) {
                currentMap.put(cUser.getId(), cUser.getName());
        }

        for (User pUser : previous) {
            previousMap.put(pUser.getId(), pUser.getName());
        }

        for (Integer cKey : currentMap.keySet()) {
                if (!previousMap.containsKey(cKey)) {
                    added++;
                }
        }

        for (Integer pKey : previousMap.keySet()) {
            if (!currentMap.containsKey(pKey)) {
                deleted++;
            } else if (!previousMap.get(pKey).equals(currentMap.get(pKey))) {
                changed++;
            }
        }
        deleted = previous.size() - current.size() + added;
        Info info = new Info(added, changed, deleted);
        return info;
    }*/
}

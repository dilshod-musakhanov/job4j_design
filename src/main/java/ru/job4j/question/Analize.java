package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
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
    }
}

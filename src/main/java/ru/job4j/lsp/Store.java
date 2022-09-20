package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface Store {
    boolean validate(Food food, int expireInPctUpToday);
    boolean add(Food food, int expireInPctUpToday);
    List<Food> findAll();
    List<Food> findByNameAndReturnFoodList(String name);
    Food findByNameAndReturnFood(String name);

    default int getPctTillFullExpire(Food food) {
        int expireFullPeriod = (int) ChronoUnit.DAYS.between(food.getCreatedDate(), food.getExpireDate());
        int expireTillNow = (int) ChronoUnit.DAYS.between(food.getCreatedDate(), LocalDate.now());
        int res = (int) Math.floor((expireTillNow * 100) / expireFullPeriod);
        return res;
    }
}

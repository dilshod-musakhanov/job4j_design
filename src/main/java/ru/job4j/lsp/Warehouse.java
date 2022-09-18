package ru.job4j.lsp;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private List<Food> foodList = new ArrayList<>();
    private final static int EXPIRE_PCT = 25;
    private LocalDate today = LocalDate.now();
    private LocalDate createdDate;
    private LocalDate expireDate;
    private int expireDaysPassedTillToday;
    private int expireFullPeriod;
    private int expireDaysFromPct;

    @Override
    public Food validate(Food food) {
        Food result = null;
        createdDate = food.getCreatedDate();
        expireDate = food.getExpireDate();
        expireFullPeriod = (int) ChronoUnit.DAYS.between(createdDate, expireDate);
        expireDaysPassedTillToday = (int) ChronoUnit.DAYS.between(createdDate, today);
        expireDaysFromPct = (int) Math.floor(EXPIRE_PCT * expireFullPeriod / 100);
        if (expireDaysPassedTillToday <= expireDaysFromPct) {
            result = food;
        }
        return result;
    }

    @Override
    public Food add(Food food) {
        Food result = null;
        if (null != validate(food)) {
            foodList.add(food);
            result = food;
            System.out.println(result.getName() + " sent to Warehouse");
        }
        return result;
    }

    @Override
    public List<Food> findAll() {
        return List.copyOf(foodList);
    }

    @Override
    public List<Food> findByNameAndReturnFoodList(String name) {
        List<Food> result = new ArrayList<>();
        for (Food food : foodList) {
            if (food.getName() == name) {
                result.add(food);
            }
        }
        return result;
    }

    @Override
    public Food findByNameAndReturnFood(String name) {
        Food result = null;
        for (Food food : foodList) {
            if (food.getName() == name) {
                result = food;
            }
        }
        return result;
    }

}

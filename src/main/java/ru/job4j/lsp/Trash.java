package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private final static int EXPIRE_PCT_FUL = 100;
    private List<Food> foodList = new ArrayList<>();

    @Override
    public boolean validate(Food food, int expireInPctUpToday) {
        boolean result = EXPIRE_PCT_FUL <= expireInPctUpToday;
        return result;
    }

    @Override
    public boolean add(Food food, int expireInPctUpToday) {
        boolean result = false;
        if (validate(food, expireInPctUpToday)) {
            foodList.add(food);
            result = true;
            System.out.println(food.getName() + " with its "
                    + expireInPctUpToday + "% expiration passed period sent to Trash");
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
                break;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        foodList.clear();
    }
}

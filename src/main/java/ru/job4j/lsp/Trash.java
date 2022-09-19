package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Store {
    private List<Food> foodList = new ArrayList<>();
    private final static int EXPIRE_PCT_FUL = 100;

    @Override
    public Food validate(Food food, int expireInPctUpToday) {
        Food result = null;
        if (EXPIRE_PCT_FUL <= expireInPctUpToday) {
            result = food;
        }
        return result;
    }

    @Override
    public boolean add(Food food, int expireInPctUpToday) {
        boolean result = false;
        if (null != validate(food, expireInPctUpToday)) {
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
                break;
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

}

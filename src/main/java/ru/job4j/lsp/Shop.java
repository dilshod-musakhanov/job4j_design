package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private List<Food> foodList = new ArrayList<>();
    private final static int EXPIRE_PCT_FROM = 25;
    private final static int EXPIRE_PCT_UP_TO = 75;
    private final static int EXPIRE_PCT_FUL = 100;
    private final static int DISCOUNT_IN_PCT = 20;

    private Food applyDiscount(Food food) {
        food.setDiscount(DISCOUNT_IN_PCT);
        food.setPrice(food.getPrice() - (DISCOUNT_IN_PCT * food.getPrice() / 100));
        return food;
    }

    @Override
    public boolean validate(Food food, int expireInPctUpToday) {
        boolean result = false;
        if (EXPIRE_PCT_FROM <= expireInPctUpToday
                && EXPIRE_PCT_UP_TO >= expireInPctUpToday) {
            result = true;
        }
        if (EXPIRE_PCT_UP_TO < expireInPctUpToday
                && EXPIRE_PCT_FUL > expireInPctUpToday) {
            applyDiscount(food);
            result = true;
            System.out.println("Discount applied on " + food.getName());
        }
        return result;
    }

    @Override
    public boolean add(Food food, int expireInPctUpToday) {
        boolean result = false;
        if (validate(food, expireInPctUpToday)) {
            foodList.add(food);
            result = true;
            System.out.println(food.getName() + " with its "
                    + expireInPctUpToday + "% expiration passed period sent to Shop");
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

}

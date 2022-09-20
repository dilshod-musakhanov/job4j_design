package ru.job4j.lsp;

import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void checkAndDistribute(Food food) {
        for (Store store : stores) {
            int expireInPctUpToday = store.getPctTillFullExpire(food);
            if (store.add(food, expireInPctUpToday)) {
                break;
            }
        }
    }

    public void checkAndDistributeListOfFood(List<Food> foodList) {
        for (Food food : foodList) {
            for (Store store : stores) {
                int expireInPctUpToday = store.getPctTillFullExpire(food);
                if (store.add(food, expireInPctUpToday)) {
                    break;
                }
            }
        }
    }

}

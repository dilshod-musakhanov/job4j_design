package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;
    private List<Food> foodList;

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

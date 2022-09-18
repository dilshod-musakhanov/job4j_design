package ru.job4j.lsp;

import java.util.List;

public interface Store {
    Food validate(Food food);
    Food add(Food food);
    List<Food> findAll();
    List<Food> findByNameAndReturnFoodList(String name);
    Food findByNameAndReturnFood(String name);
}

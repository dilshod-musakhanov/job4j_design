package ru.job4j.srp;

/*
* Класс описывает намеренное нарушение первого принципа SOLID
SRP (Single Responsibility Principle).
* Класс имеет большое количество методов/зону ответственности.
* Методы order, deliver, addonPizza, applySpecialOffer, generateSalesReport, getLocation,
bestPerformedPizzeriaOfTheMonth должны быть в других соответствующих им классах/интерфейсах.
*/

public class Pizza {
    public Pizza make() {
        return new Pizza();
    }

    public void order() {
        System.out.println("ordered by phone");
    }

    public void deliver() {
        System.out.println("delivered in 1 hour");
    }

    public void addonPizza() {
        System.out.println("extra cheese added");
    }

    public void applySpecialOffer() {
        System.out.println("10% discount applied");
    }

    public void generateSalesReport() {
        System.out.println("Spicy Chicken Ranch is the most sold pizza of the month");
    }

    public void getLocation() {
        System.out.println("Al Khail Gate");
    }
}


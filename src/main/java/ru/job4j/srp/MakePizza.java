package ru.job4j.srp;

/*
*  Интерфейс описывает намеренное нарушение первого принципа SOLID
SRP (Single Responsibility Principle).
* Методы deliver, applySpecialOffer должны быть в других соответствующих интерфейсах.
*/

public interface MakePizza {
    Pizza make();
    void deliver();
    void applySpecialOffer();
}

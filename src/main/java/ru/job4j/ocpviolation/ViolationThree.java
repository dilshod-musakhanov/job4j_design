package ru.job4j.ocpviolation;

/**
 * Принцип OCP нарушается, так как если надо будет записать в БД, то потребуется изменить класс, добавив метод.
 */

public class ViolationThree {

    public static class Writer {
        public void saveToFile() {
            System.out.println("saved to file");
        }
    }
}

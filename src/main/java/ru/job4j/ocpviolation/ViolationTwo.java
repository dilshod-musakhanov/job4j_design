package ru.job4j.ocpviolation;

/**
 * Принцип OCP нарушается, так как метод playGame принимает параметр не типа абстракции
 */

public class ViolationTwo {

    public static class Iphone implements Phone {
        @Override
        public void playMelody() {
            System.out.println("la laa la la");
        }
    }

    public void playGame(Iphone iphone) {
        System.out.println("Mortal Combat begins");
    }

    public interface Phone {
        void playMelody();
    }
}

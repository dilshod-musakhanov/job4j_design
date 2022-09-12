package ru.job4j.ocpviolation;

/**
 * Принцип OCP нарушается, так как для расширения функционала animalSound() требуется
 дописывать код
 */

public class ViolationOne {
    public static void main(String[] args) {
        new ViolationOne().animalSound();
    }

    public void animalSound() {
        new Dog().soundWow();
        new Cat().soundMeow();
    }

    public static class Dog {
        public void soundWow() {
            System.out.println("Wow-Wow");
        }
    }

    public static class Cat {
        public void soundMeow() {
            System.out.println("Meow-Meow");
        }
    }
}

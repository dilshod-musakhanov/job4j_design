package ru.job4j.lspviolation;

/**
 * Класс нарушеат принцип LSP: в классе NightClub усилено предусловие.
 */

public class LspViolationOne {

    public class Club {
        private int age;

        public Club(int age) {
            this.age = age;
        }

        public void join(int age) {
            if (age < 18) {
                throw new IllegalArgumentException("You are still young to join");
            } else {
                System.out.println("Welcome to the club");
            }
        }

    }

    public class ShootingClub extends Club {

        public ShootingClub(int age) {
            super(age);
        }

        @Override
        public void join(int age) {
            if (age < 21) {
                throw new IllegalArgumentException("You are still young to join");
            } else {
                System.out.println("Welcome to the club");
            }
        }
    }
}

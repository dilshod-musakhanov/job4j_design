package ru.job4j.isp.menu;

import java.util.List;
import java.util.Scanner;

public class TODOApp {
    private static final int ADD_ROOT = 1;
    private static final int ADD_TO_ROOT = 2;
    private static final int SHOW_ALL_ITEMS = 3;
    private static final int EXIT = 0;
    public static final ActionDelegate STUB_ACTION = System.out::println;

    private void menu() {
        System.out.println("Меню");
        List<String> list = List.of(
                "Добавить запись в корень",
                "Добавить запись под корень",
                "Показать записи"
        );
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println("0. Выход");
    }

    public static void main(String[] args) {
        TODOApp todoApp = new TODOApp();
        Menu menu = new SimpleMenu();
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            todoApp.menu();
            int option = Integer.parseInt(scanner.nextLine());
            if (option == ADD_ROOT) {
                System.out.println("Введите новый корень:");
                String newRoot = scanner.nextLine();
                boolean isAdded = menu.add(Menu.ROOT, newRoot, STUB_ACTION);
                System.out.println(isAdded ? "Добавлено" : "Не добавлено");
            } else if (option == ADD_TO_ROOT) {
                System.out.println("Введите корень:");
                String oldRoot = scanner.nextLine();
                System.out.println("Введите добавление:");
                String child = scanner.nextLine();
                boolean isAdded = menu.add(oldRoot, child, STUB_ACTION);
                System.out.println(isAdded ? "Добавлено" : "Не добавлено");
            } else if (option == SHOW_ALL_ITEMS) {
                System.out.println("Все записи:");
                menuPrinter.print(menu);
            } else if (option == EXIT) {
                flag = false;
                System.out.println("Exit");
            } else {
                System.out.println("Неверный номер! Введите номер по меню");
            }
        }
    }
}

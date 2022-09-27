package ru.job4j.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private static final String LINE = "---";

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo info : menu) {
            int count = info.getNumber().split("\\.").length - 1;
            System.out.println(LINE.repeat(count) + info.getNumber() + info.getName());
        }
    }

}

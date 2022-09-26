package ru.job4j.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    private static final String LINE = "---";
    private static final String LS = System.lineSeparator();

    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo info : menu) {
            String outputStr = info.getName() + " " + info.getNumber();
            int sequenceNumberLength = info.getNumber().split("\\.").length;
            if (sequenceNumberLength == 1) {
                stringBuilder.append(outputStr).append(LS);
            } else {
                stringBuilder.append(LINE.repeat(sequenceNumberLength - 1))
                        .append(outputStr)
                        .append(LS);
            }
        }
        System.out.println(stringBuilder.toString());
    }

}

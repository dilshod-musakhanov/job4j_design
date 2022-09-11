package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class AccountsReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private static final String LS = System.lineSeparator();

    private int bonus = 10;

    Store store;

    public AccountsReportEngine(Store store) {
        this.store = store;
    }

    public double addBonus(double salary, int bonus) {
        return salary + ((salary * bonus) / 100);
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS);
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(DATE_FORMAT.format(employee.getHired().getTime())).append(";")
                    .append(DATE_FORMAT.format(employee.getFired().getTime())).append(";")
                    .append(addBonus(employee.getSalary(), bonus)).append(";")
                    .append(LS);
        }
        return text.toString();
    }

}

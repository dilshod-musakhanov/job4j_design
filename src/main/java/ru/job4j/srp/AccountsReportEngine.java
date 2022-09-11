package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class AccountsReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private static final String LS = System.lineSeparator();

    public static final int BONUS = 10;

    public static final int TO_GET_10_PCT = 100;

    private Store store;

    public AccountsReportEngine(Store store) {
        this.store = store;
    }

    public double addBonus(double salary, int bonus) {
        return salary + ((salary * bonus) / TO_GET_10_PCT);
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
                    .append(addBonus(employee.getSalary(), BONUS)).append(";")
                    .append(LS);
        }
        return text.toString();
    }

}

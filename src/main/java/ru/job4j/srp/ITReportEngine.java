package ru.job4j.srp;

import java.text.SimpleDateFormat;
import java.util.function.Predicate;

public class ITReportEngine implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private static final String LS = System.lineSeparator();

    private Store store;

    public ITReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder()
                .append("<html lang=\"en\">").append(LS)
                .append("<head>").append(LS)
                .append("meta charset=\"UTF-8\">").append(LS)
                .append("<title>").append("IT Report").append("</title>").append(LS)
                .append("</head>").append(LS)
                .append("<body>").append(LS)
                .append("<table>").append(LS)
                        .append("<tr>").append(LS)
                        .append("<th>").append("Name").append("</th>").append(LS)
                        .append("<th>").append("Hired").append("</th>").append(LS)
                        .append("<th>").append("Fired").append("</th>").append(LS)
                        .append("<th>").append("Salary").append("</th>").append(LS)
                        .append("</tr>").append(LS);
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr>")
                    .append(LS).append("<td>").append(employee.getName()).append("</td>").append(LS)
                    .append("<td>").append(DATE_FORMAT.format(employee.getHired().getTime()))
                    .append("</td>").append(LS)
                    .append("<td>").append(DATE_FORMAT.format(employee.getFired().getTime()))
                    .append("</td>").append(LS)
                    .append("<td>").append(employee.getSalary()).append("</td>").append(LS)
                    .append("</tr>").append(LS);
        }
        text.append("</table>").append(LS)
                .append("</body>").append(LS)
                .append("</html>").append(LS);
        return text.toString();
    }
}

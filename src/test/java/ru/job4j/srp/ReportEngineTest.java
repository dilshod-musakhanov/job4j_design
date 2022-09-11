package ru.job4j.srp;

import org.junit.Test;
import java.util.Calendar;

import static ru.job4j.srp.ReportEngine.DATE_FORMAT;
import static org.assertj.core.api.Assertions.*;

public class ReportEngineTest {

    private final static String LS = System.lineSeparator();

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ReportEngine engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(worker.getSalary()).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenHrGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100000.00);
        Employee worker2 = new Employee("Lana", now, now, 200000.00);
        Employee worker3 = new Employee("Peter", now, now, 300000.00);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        HrReportEngine engine = new HrReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(LS)
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(LS)
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(LS)
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenAccountsReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        int bonus = 10;
        AccountsReportEngine engine = new AccountsReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(engine.addBonus(worker.getSalary(), bonus)).append(";")
                .append(LS);
        assertThat(engine.generate(em -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenITReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        ITReportEngine engine = new ITReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<table>").append(LS)
                .append("<tr>").append(LS)
                .append("<th>").append("Name").append("</th>").append(LS)
                .append("<th>").append("Hired").append("</th>").append(LS)
                .append("<th>").append("Fired").append("</th>").append(LS)
                .append("<th>").append("Salary").append("</th>").append(LS)
                .append("</tr>").append(LS)
                .append("<tr>").append(LS).append("<td>").append(worker.getName()).append("</td>").append(LS)
                .append("<td>").append(DATE_FORMAT.format(worker.getHired().getTime()))
                .append("</td>").append(LS)
                .append("<td>").append(DATE_FORMAT.format(worker.getFired().getTime()))
                .append("</td>").append(LS)
                .append("<td>").append(worker.getSalary()).append("</td>").append(LS)
                .append("</tr>").append(LS)
                .append("</table>");
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }
}
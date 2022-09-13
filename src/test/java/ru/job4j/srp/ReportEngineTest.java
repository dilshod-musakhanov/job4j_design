package ru.job4j.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
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
        AccountsReportEngine engine = new AccountsReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(LS)
                .append(worker.getName()).append(";")
                .append(DATE_FORMAT.format(worker.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker.getFired().getTime())).append(";")
                .append(engine.addBonus(worker.getSalary(), AccountsReportEngine.BONUS)).append(";")
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
                .append("</tr>").append(LS)
                .append("<tr>").append(LS).append("<td>").append(worker.getName()).append("</td>").append(LS)
                .append("<td>").append(DATE_FORMAT.format(worker.getHired().getTime()))
                .append("</td>").append(LS)
                .append("<td>").append(DATE_FORMAT.format(worker.getFired().getTime()))
                .append("</td>").append(LS)
                .append("<td>").append(worker.getSalary()).append("</td>").append(LS)
                .append("</tr>").append(LS)
                .append("</table>").append(LS)
                .append("</body>").append(LS)
                .append("</html>").append(LS);
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenJsonReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        JsonReportEngine engine = new JsonReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("[").append("{\"name\":").append("\"Ivan\",")
                .append("\"hired\":")
                .append("{\"year\":").append(now.get(Calendar.YEAR)).append(",\"month\":")
                .append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(now.get(Calendar.MINUTE)).append(",\"second\":")
                .append(now.get(Calendar.SECOND)).append("},")
                .append("\"fired\":")
                .append("{\"year\":").append(now.get(Calendar.YEAR)).append(",\"month\":")
                .append(now.get(Calendar.MONTH)).append(",\"dayOfMonth\":")
                .append(now.get(Calendar.DAY_OF_MONTH)).append(",\"hourOfDay\":")
                .append(now.get(Calendar.HOUR_OF_DAY)).append(",\"minute\":")
                .append(now.get(Calendar.MINUTE)).append(",\"second\":")
                .append(now.get(Calendar.SECOND)).append("},")
                .append("\"salary\":").append(worker.getSalary()).append("}]");
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }

    @Test
    public void whenXmlReportGenerated() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100000);
        store.add(worker);
        XmlReportEngine engine = new XmlReportEngine(store);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employee name=\"Ivan\" salary=\"100000.0\">\n")
                .append("    <hired>").append(formatter.format(worker.getHired().getTime())).append("</hired>\n")
                .append("    <fired>").append(formatter.format(worker.getFired().getTime())).append("</fired>\n")
                .append("</employee>\n");
        assertThat(engine.generate(emp -> true)).isEqualTo(expect.toString());
    }
}
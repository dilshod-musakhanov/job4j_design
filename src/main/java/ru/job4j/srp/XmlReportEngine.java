package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private Store store;

    private StringBuilder result = new StringBuilder();

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        List<Employee> temp = store.findBy(filter);
        for (Employee emp : temp) {
            Employee employee = new Employee(
                    emp.getName(),
                    emp.getHired(),
                    emp.getFired(),
                    emp.getSalary()
            );
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(employee, writer);
                result.append(writer.getBuffer());
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }
        return result.toString();
    }

}

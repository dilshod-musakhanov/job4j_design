package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {

    private Store store;

    private StringBuilder result = new StringBuilder();

    public XmlReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> temp = store.findBy(filter);
        JAXBContext context;
        Marshaller marshaller;
        String result;
        try {
            context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(temp), writer);
            result = writer.getBuffer().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

}

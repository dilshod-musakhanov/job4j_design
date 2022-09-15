package ru.job4j.srp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.function.Predicate;

public class XmlReportEngine implements Report {
    private Store store;
    private JAXBContext context;
    private Marshaller marshaller;

    public XmlReportEngine(Store store) {
        this.store = store;
        try {
            context = JAXBContext.newInstance(Employees.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        String result;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            result = writer.getBuffer().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return result;
    }

}

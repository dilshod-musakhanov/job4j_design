package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        Person person = new Person(false, 30, new Contact("11-111"), "worker", "married");
        JAXBContext context = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
        System.out.println();
        System.out.println();
        HolidayPackage holidayPackage = new HolidayPackage(
                true, "Dubai", 7, "USD 2500", new SpecialOffer("30%"),
                new String[] {"Air Fare", "Accommodation", "All Inclusive"}
        );
        JAXBContext context1 = JAXBContext.newInstance(HolidayPackage.class);
        Marshaller marshaller1 = context1.createMarshaller();
        marshaller1.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml1 = "";
        try (StringWriter writer1 = new StringWriter()) {
            marshaller1.marshal(holidayPackage, writer1);
            xml1 = writer1.getBuffer().toString();
            System.out.println(xml1);
        }
        Unmarshaller unmarshaller1 = context1.createUnmarshaller();
        try (StringReader reader1 = new StringReader(xml1)) {
            HolidayPackage resultHolidayPackage = (HolidayPackage) unmarshaller1.unmarshal(reader1);
            System.out.println(resultHolidayPackage);
        }
    }
}

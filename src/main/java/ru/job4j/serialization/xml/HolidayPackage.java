package ru.job4j.serialization.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "holidaypackage")
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayPackage {
    private static final Logger LOG = LoggerFactory.getLogger(HolidayPackage.class.getName());

    @XmlAttribute
    private boolean onSale;

    @XmlAttribute
    private String destination;

    @XmlAttribute
    private int nights;

    @XmlAttribute
    private String cost;
    private SpecialOffer discount;

    @XmlElementWrapper(name = "inclusions")
    @XmlElement(name = "inclusion")
    private String[] inclusions;

    public HolidayPackage() {

    }

    public HolidayPackage(boolean onSale, String destination, int nights, String cost, SpecialOffer discount, String[] inclusions) {
        this.onSale = onSale;
        this.destination = destination;
        this.nights = nights;
        this.cost = cost;
        this.discount = discount;
        this.inclusions = inclusions;
    }

    @Override
    public String toString() {
        return "HolidayPackage{"
                + "onSale=" + onSale
                + ", destination='" + destination + '\''
                + ", nights=" + nights
                + ", inclusions=" + Arrays.toString(inclusions)
                + ", cost=" + cost
                + ", discount=" + discount
                + '}';
    }

    public static void main(String[] args) throws JAXBException {
        HolidayPackage holidayPackage = new HolidayPackage(
                true, "Dubai", 7, "USD 2500", new SpecialOffer("30%"),
                new String[] {"Air Fare", "Accommodation", "All Inclusive"}
        );
        JAXBContext context = JAXBContext.newInstance(HolidayPackage.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(holidayPackage, writer);
            String result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            LOG.error("Exception in JAXBContext");
        }
    }
}

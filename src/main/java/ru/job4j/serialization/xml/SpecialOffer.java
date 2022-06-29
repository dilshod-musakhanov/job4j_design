package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "specialoffer")
public class SpecialOffer {

    @XmlAttribute
    private String discount;

    public SpecialOffer() {
    }

    public SpecialOffer(String discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "SpecialOffer{"
                + "discount='" + discount + '\''
                + '}';
    }
}

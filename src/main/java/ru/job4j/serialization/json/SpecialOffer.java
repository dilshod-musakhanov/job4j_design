package ru.job4j.serialization.json;

public class SpecialOffer {
    String discount;

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

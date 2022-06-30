package ru.job4j.serialization.json;

public class SpecialOffer {
    String discount;

    public SpecialOffer(String discount) {
        this.discount = discount;
    }

    public String getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "SpecialOffer{"
                + "discount='" + discount + '\''
                + '}';
    }
}

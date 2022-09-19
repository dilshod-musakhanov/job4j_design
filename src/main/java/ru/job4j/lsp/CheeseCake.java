package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.Objects;

public class CheeseCake extends Food {
    private String name;
    private LocalDate createdDate;
    private LocalDate expireDate;
    private double price;
    private int discount;

    public CheeseCake(String name, LocalDate createdDate, LocalDate expireDate, double price) {
        this.name = name;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public CheeseCake(String name, LocalDate createdDate, LocalDate expireDate, double price, int discount) {
        this.name = name;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CheeseCake)) {
            return false;
        }
        CheeseCake cheese = (CheeseCake) o;
        return Double.compare(cheese.getPrice(), getPrice()) == 0 && Objects.equals(getName(), cheese.getName())
                && Objects.equals(getCreatedDate(), cheese.getCreatedDate())
                && Objects.equals(getExpireDate(), cheese.getExpireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreatedDate(), getExpireDate(), getPrice());
    }

    @Override
    public String toString() {
        return "Cheese{"
                + "name='" + name + '\''
                + ", createdDate=" + createdDate
                + ", expireDate=" + expireDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}

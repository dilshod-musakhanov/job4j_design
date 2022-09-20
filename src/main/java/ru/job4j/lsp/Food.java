package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Food {

    private String name;
    private LocalDate createdDate;
    private LocalDate expireDate;
    private double price;
    private int discount;

    public Food(String name, LocalDate createdDate, LocalDate expireDate, double price, int discount) {
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
        if (!(o instanceof Food)) {
            return false;
        }
        Food food = (Food) o;
        return Double.compare(food.getPrice(), getPrice()) == 0
                && Objects.equals(getName(), food.getName())
                && Objects.equals(getCreatedDate(), food.getCreatedDate())
                && Objects.equals(getExpireDate(), food.getExpireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreatedDate(), getExpireDate(), getPrice());
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", createdDate=" + createdDate
                + ", expireDate=" + expireDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}

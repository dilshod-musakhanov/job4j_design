package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.Objects;

public class MeetBalls extends Food {
    private String name;
    private LocalDate createdDate;
    private LocalDate expireDate;
    private double price;
    private int discount;

    public MeetBalls(String name, LocalDate createdDate, LocalDate expireDate, double price) {
        this.name = name;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.price = price;
    }

    public MeetBalls(String name, LocalDate createdDate, LocalDate expireDate, double price, int discount) {
        this.name = name;
        this.createdDate = createdDate;
        this.expireDate = expireDate;
        this.price = price;
        this.discount = discount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public LocalDate getExpireDate() {
        return expireDate;
    }

    @Override
    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getDiscount() {
        return discount;
    }

    @Override
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetBalls)) {
            return false;
        }
        MeetBalls meetBalls = (MeetBalls) o;
        return Double.compare(meetBalls.getPrice(), getPrice()) == 0 && Objects.equals(getName(), meetBalls.getName()) && Objects.equals(getCreatedDate(), meetBalls.getCreatedDate()) && Objects.equals(getExpireDate(), meetBalls.getExpireDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCreatedDate(), getExpireDate(), getPrice());
    }

    @Override
    public String toString() {
        return "MeetBalls{"
                + "name='" + name + '\''
                + ", createdDate=" + createdDate
                + ", expireDate=" + expireDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}

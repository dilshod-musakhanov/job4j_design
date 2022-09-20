package ru.job4j.lsp;

import java.time.LocalDate;

public class HomeBread extends Food {
    public HomeBread(String name, LocalDate createdDate, LocalDate expireDate, double price, int discount) {
        super(name, createdDate, expireDate, price, discount);
    }
}

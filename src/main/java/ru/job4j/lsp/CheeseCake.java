package ru.job4j.lsp;

import java.time.LocalDate;

public class CheeseCake extends Food {
    public CheeseCake(String name, LocalDate createdDate, LocalDate expireDate, double price, int discount) {
        super(name, createdDate, expireDate, price, discount);
    }
}

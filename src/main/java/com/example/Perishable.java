package com.example;

import java.time.LocalDate;

/*
 * Interface för produkter som kan förvaras
 */
public interface  Perishable {

    LocalDate expirationDate();

    //Check för utgången produkt
    default boolean isExpired() {
        return expirationDate().isBefore(LocalDate.now());
    }
}
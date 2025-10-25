package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

  /*
  * Abstrakt basklass för produkter
  * Innehåller id, namn, kategori och pris
  */
public abstract class Product {

    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    protected Product(UUID id, String name, Category category, BigDecimal price) {
        this.id = Objects.requireNonNull(id);
        this.name = Objects.requireNonNull(name);
        this.category = Objects.requireNonNull(category);
        this.price = Objects.requireNonNull(price);
    }

    //Getters
    public UUID uuid() { return id; }
    public String name() { return name; }
    public Category category() { return category; }
    public BigDecimal price() { return price; }

    //Setter för pris
    public void price(BigDecimal newPrice) { this.price = Objects.requireNonNull(newPrice); }

    //Abstrakt metod för produktdetaljer
    public abstract String productDetails();
}
package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

  /*
  * Matprodukt som är både perishable och shippable.
  */
public class FoodProduct extends Product implements Perishable, Shippable {

  private final LocalDate expirationDate;
  private final BigDecimal weight; // i kg

  public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
    super(id, name, category, price);

    if (price.compareTo(BigDecimal.ZERO) < 0)
      throw new IllegalArgumentException("Price cannot be negative.");
    if (weight.compareTo(BigDecimal.ZERO) < 0)
      throw new IllegalArgumentException("Weight cannot be negative.");

    
    this.expirationDate = Objects.requireNonNull(expirationDate);
    this.weight = Objects.requireNonNull(weight);
  }

  @Override 
  public LocalDate expirationDate() {
    return expirationDate;
  }

  @Override
  public double weight() {
    return weight.doubleValue();
  }

  @Override
  public BigDecimal calculateShippingCost() {
    return weight.multiply(BigDecimal.valueOf(50));
  }

  @Override
  public String productDetails() {
    return "Food: " + name() + ", Expires: " + expirationDate;
  }


}
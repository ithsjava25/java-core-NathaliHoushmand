package com.example;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

/**
 * Elektronikprodukt som är shippable.
 */

public class ElectronicsProduct extends Product implements Shippable {

    private final int warrantyPeriod;   
    private final BigDecimal weight;    // i kg

    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price,
                              int warrantyPeriod, BigDecimal weight) {
        super(id, name, category, price);

        if (warrantyPeriod < 0)
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        if (weight.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");

        this.warrantyPeriod = warrantyPeriod;
        this.weight = Objects.requireNonNull(weight);
    }

    @Override
    public double weight() {
        return weight.doubleValue();
    }

    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal cost = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            cost = cost.add(BigDecimal.valueOf(49));
        }
        return cost;
    }

    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyPeriod + " months";
    }
}


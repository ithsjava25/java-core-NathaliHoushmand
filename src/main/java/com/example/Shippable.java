package com.example;

import java.math.BigDecimal;

/*
 * Interface för produkter som kan skickas
 */
public interface Shippable {

    double weight(); //Vikt i kg

    BigDecimal calculateShippingCost(); // fraktkostnad
}
package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

/**
 * Representerar en produktkategori
 * Immutable och cachad för att återanvända samma instans
 */
public final class Category {

    // Cache för att hålla unika instantser
    private static final Map<String, Category> CACHE = new ConcurrentHashMap<>();

    private final String name;

    // Pricat konstruktor
    private Category(String name) {
        this.name = name;
    }

     /**
     * Skapar eller hämtar en Category
     * Validerar input och normaliserar den
     */
    public static Category of(String name){
        if (name == null)
            throw new IllegalArgumentException("Category name can't be null");
        if (name.isBlank())
            throw new IllegalArgumentException("Category name can't be blank");

        // Fixar bokstävernas förhållanden
        String normalized = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

        // Returnera befintlig eller ny instans
        return CACHE.computeIfAbsent(normalized, Category::new);
    }

    public String name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category that = (Category) o;
        return name.equals(that.name);
    }

    @Override 
    public int hashCode() {
        return Objects.hash(name);
    }


    
}
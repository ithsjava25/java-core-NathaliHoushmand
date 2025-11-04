package com.example;

import java.util.*;
import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Singleton warehouse per namn.
 */
public class Warehouse {

    // Cache för singleton-instanser per namn
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();

    private final String name;
    private final Map<UUID, Product> products = new LinkedHashMap<>();
    private final Set<UUID> changedProducts = new HashSet<>();

    private Warehouse(String name) {
        this.name = Objects.requireNonNull(name);
    }

    // Returnera samma instans per namn
    public static Warehouse getInstance(String name) {
        return INSTANCES.computeIfAbsent(name, Warehouse::new);
    }

    public String name() {
        return name;
    }

    // Lägg till produkt
    public void addProduct(Product product) {
    if (product == null)
        throw new IllegalArgumentException("Product cannot be null.");

    if (products.containsKey(product.uuid())) {
        throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
    }

    products.put(product.uuid(), product);
}



    // Hämta produkter som unmodifiable list
    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    // Hämta produkt via UUID
    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(products.get(id));
    }

    // Uppdatera pris
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product p = products.get(id);
        if (p == null)
            throw new NoSuchElementException("Product not found with id: " + id);
        p.price(newPrice);
        changedProducts.add(id);
    }

    // Returnera ändrade produkter
    public List<Product> getChangedProducts() {
        return changedProducts.stream()
                .map(products::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    // Returnera utgångna produkter
    public List<Perishable> expiredProducts() {
    return products.values().stream()
            .filter(p -> p instanceof Perishable perishable && perishable.isExpired())
            .map(p -> (Perishable) p)
            .collect(Collectors.toList());
}


    // Returnera shippable produkter
    public List<Shippable> shippableProducts() {
        return products.values().stream()
                .filter(p -> p instanceof Shippable)
                .map(p -> (Shippable) p)
                .collect(Collectors.toList());
    }

    // Ta bort produkt
    public void remove(UUID id) {
        products.remove(id);
        changedProducts.remove(id);
    }

    public void clearProducts() {
    products.clear();
    changedProducts.clear();
}

public boolean isEmpty() {
    return products.isEmpty();
}

public static Warehouse getInstance() {
    return getInstance("Default");
}

public Map<Category, List<Product>> getProductsGroupedByCategories() {
    return products.values().stream()
            .collect(Collectors.groupingBy(Product::category));
}




}


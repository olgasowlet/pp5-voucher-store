package pl.sowol.voucherstore.productcatalog;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class HashMapProductStorage implements ProductStorage {
    Map<String, Product> productsStorage;

    public HashMapProductStorage() {
        this.productsStorage = new ConcurrentHashMap<>();
    }

    @Override
    public List<Product> allPublished() {
        return productsStorage.values()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPrice() != null)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.ofNullable(productsStorage.get(productId));
    }

    @Override
    public void save(Product newProduct) {
        productsStorage.put(newProduct.getId(), newProduct);
    }
}

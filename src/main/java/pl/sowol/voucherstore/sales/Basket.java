package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.Product;
import pl.sowol.voucherstore.sales.exceptions.NotEnoughQuantityException;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Basket {

    private final HashMap<String, Product> products;
    private final HashMap<String, Integer> productsCount;

    public Basket() {
        products = new HashMap<>();
        productsCount = new HashMap<>();
    }

    public static Basket empty() {
        return new Basket();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public void add(Product product, Inventory inventory) {
        if (!inventory.isAvailable(product.getId())) {
            throw new NotEnoughQuantityException();
        }
        if (isInBasket(product)){
            increaseQuantity(product);
        } else {
            putIntoBasket(product);
        }
    }

    private void putIntoBasket(Product product) {
        products.put(product.getId(), product);
        productsCount.put(product.getId(), 1);
    }

    private void increaseQuantity(Product product) {
        productsCount.put(product.getId(), productsCount.get(product.getId()) + 1);
    }

    private boolean isInBasket(Product product) {
        return productsCount.containsKey(product.getId());
    }

    public int getProductsCount() {
        return products.size();
    }

    public List<BasketItem> getProductsList() {
        return productsCount.entrySet()
                .stream()
                .map(es -> new BasketItem(es.getKey(), es.getValue()))
                .collect(Collectors.toList());
    }
}

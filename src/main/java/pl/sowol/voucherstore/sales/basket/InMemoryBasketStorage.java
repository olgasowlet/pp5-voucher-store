package pl.sowol.voucherstore.sales.basket;

import pl.sowol.voucherstore.sales.basket.Basket;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBasketStorage {

    private Map<String, Basket> baskets;

    public InMemoryBasketStorage() {
        baskets = new ConcurrentHashMap<>();
    }

    public Optional<Basket> loadForCustomer(String customerId) {
        return Optional.ofNullable(baskets.get(customerId));
    }

    public void addForCustomer(String customerId, Basket basket) {
        baskets.put(customerId, basket);
    }
}

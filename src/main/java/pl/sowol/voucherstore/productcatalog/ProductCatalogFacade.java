package pl.sowol.voucherstore.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ProductCatalogFacade {

    ConcurrentHashMap<String, Product> products;

    public ProductCatalogFacade() {
        this.products = new ConcurrentHashMap<>();
    }

    public String createProduct() {
        Product newProduct = new Product(UUID.randomUUID());

        products.put(newProduct.getId(), newProduct);

        return newProduct.getId();
    }

    public boolean isExistById(String productId) {
        return products.get(productId) != null;
    }

    public Product getById(String productId) {
        return products.get(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) {
        Product loaded = products.get(productId);
        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);
    }

    public void applyPrice(String productId, BigDecimal valueOf) {
        Product loaded = products.get(productId);
        loaded.setPrice(valueOf);
    }

    public List<Product> getAllAvaiableProducts() {
        return products.values()
                .stream()
                .filter(p -> p.getDescription() != null)
                .filter(p -> p.getPrice() != null)
                .collect(Collectors.toList());
    }
}

package pl.sowol.voucherstore.productcatalog;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ProductCatalogFacade {
    ProductStorage productStorage;

    public ProductCatalogFacade(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public String createProduct() {
        Product newProduct = new Product(UUID.randomUUID());
        productStorage.save(newProduct);

        return newProduct.getId();
    }

    public boolean isExistById(String productId) {

        return productStorage.getById(productId).isPresent();
    }

    public Product getById(String productId) throws ProductNotFoundException {
        return getProductOrException(productId);
    }

    public void updateDetails(String productId, String productDesc, String productPicture) throws ProductNotFoundException {
        Product loaded = getProductOrException(productId);

        loaded.setDescription(productDesc);
        loaded.setPicture(productPicture);
    }

    public void applyPrice(String productId, BigDecimal valueOf) throws ProductNotFoundException {
        Product loaded = getProductOrException(productId);

        loaded.setPrice(valueOf);
    }

    public List<Product> getAllAvaiableProducts() {
        return productStorage.allPublished();

    }

    private Product getProductOrException(String productId) throws ProductNotFoundException {
        return productStorage.getById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("There is no product with id: %s", productId)));
    }

}

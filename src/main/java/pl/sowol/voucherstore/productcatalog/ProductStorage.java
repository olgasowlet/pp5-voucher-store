package pl.sowol.voucherstore.productcatalog;

import java.util.List;
import java.util.Optional;

public interface ProductStorage {
    List<Product> allPublished();

    Optional<Product> getById(String productId);

    void save(Product newProduct);
}

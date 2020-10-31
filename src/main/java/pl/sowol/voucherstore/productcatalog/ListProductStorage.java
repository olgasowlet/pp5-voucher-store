package pl.sowol.voucherstore.productcatalog;

import java.util.List;
import java.util.Optional;

public class ListProductStorage implements ProductStorage {

    @Override
    public List<Product> allPublished() {
        return null;
    }

    @Override
    public Optional<Product> getById(String productId) {
        return Optional.empty();
    }

    @Override
    public void save(Product newProduct) {

    }
}

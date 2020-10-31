package pl.sowol.voucherstore.productcatalog;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class ListProductStorageTest {

    @Test
    public void itAllowsStoreProduct() {
        Product p = thereIsNewPublishedProduct();
        ProductStorage storage = new ListProductStorage();

        storage.save(p);

        assertThat(storage.allPublished())
                .hasSize(1)
                .extracting(Product::getId)
                .contains(p.getId());
    }

    private static Product thereIsNewPublishedProduct() {
        return null;
    }
}

package pl.sowol.voucherstore.sales.product;

import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.productcatalog.Product;

public class ProductCatalogProductDetailsProvider implements ProductDetailsProvider {

    private ProductCatalogFacade productCatalogFacade;

    public ProductCatalogProductDetailsProvider(ProductCatalogFacade productCatalogFacade) {
        this.productCatalogFacade = productCatalogFacade;
    }

    @Override
    public ProductDetails getByProductId(String productId) {
        Product product = productCatalogFacade.getById(productId);

        return new ProductDetails(productId, product.getDescription(), product.getPrice());
    }
}

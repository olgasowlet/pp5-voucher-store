package pl.sowol.voucherstore.productcatalog;

public class ProductCatalogConfiguration {
    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade();
    }
}

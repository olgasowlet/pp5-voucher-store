package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {

    protected ProductCatalogFacade productCatalog;
    protected InMemoryBasketStorage basketStorage;
    protected Inventory alwaysExists;
    protected CurrentCustomerContext currentCustomerContext;
    protected String customerId;

    protected CurrentCustomerContext thereIsCurrentCustomerContext() {
        return () -> customerId;
    }

    protected Inventory thereIsInventory() {
        return productId -> true;
    }

    protected InMemoryBasketStorage thereIsBasketStore() {
        return new InMemoryBasketStorage();
    }

    protected ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }

    protected String thereIsProductAvaiable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateDetails(id, "lego", "http://picture");

        return id;
    }

    protected SalesFacade thereIsSalesModule() {
        return new SalesFacade(basketStorage, productCatalog, currentCustomerContext, alwaysExists);
    }

    protected String thereIsCustomerWhoIsDoingSomeShopping() {
        return UUID.randomUUID().toString();
    }
}

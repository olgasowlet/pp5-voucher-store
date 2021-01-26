package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.OfferMaker;
import pl.sowol.voucherstore.sales.product.ProductDetails;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTestCase {

    protected ProductCatalogFacade productCatalog;
    protected InMemoryBasketStorage basketStorage;
    protected Inventory alwaysExists;
    protected CurrentCustomerContext currentCustomerContext;
    protected String customerId;
    protected OfferMaker offerMaker;

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
        return new SalesFacade(basketStorage, productCatalog, currentCustomerContext, alwaysExists, offerMaker);
    }

    protected OfferMaker thereIsOfferMaker(ProductCatalogFacade productCatalogFacade) {
        return new OfferMaker(productId -> {
            var product = productCatalogFacade.getById(productId);

            return new ProductDetails(product.getId(), product.getDescription(), product.getPrice());
        });
    }

    protected String thereIsCustomerWhoIsDoingSomeShopping() {
        return UUID.randomUUID().toString();
    }
}

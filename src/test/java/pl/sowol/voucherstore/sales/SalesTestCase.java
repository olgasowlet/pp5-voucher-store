package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.Product;
import pl.sowol.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.OfferMaker;
import pl.sowol.voucherstore.sales.ordering.InMemoryReservationRepository;
import pl.sowol.voucherstore.sales.ordering.ReservationRepository;
import pl.sowol.voucherstore.sales.payment.InMemoryPaymentGateway;
import pl.sowol.voucherstore.sales.payment.PaymentGateway;
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
    protected PaymentGateway paymentGateway;
    protected ReservationRepository reservationRepository;

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

    protected PaymentGateway thereIsInMemoryPaymentGateway() {
        return new InMemoryPaymentGateway();
    }

    protected String thereIsProductAvaiable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateDetails(id, "lego", "http://picture");

        return id;
    }

    protected SalesFacade thereIsSalesModule() {
        return new SalesFacade(basketStorage, productCatalog, currentCustomerContext, alwaysExists, offerMaker, paymentGateway, reservationRepository);
    }

    protected OfferMaker thereIsOfferMaker(ProductCatalogFacade productCatalogFacade) {
        return new OfferMaker(productId -> {
            Product product = productCatalogFacade.getById(productId);

            return new ProductDetails(productId, product.getDescription(), product.getPrice());
        });
    }

    protected String thereIsCustomerWhoIsDoingSomeShopping() {
        return UUID.randomUUID().toString();
    }

    protected ReservationRepository thereIsInMemoryReservationRepository() {
        return new InMemoryReservationRepository();
    }
}

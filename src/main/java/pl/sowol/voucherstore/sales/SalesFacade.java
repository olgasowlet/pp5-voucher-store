package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.Product;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.Basket;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.Offer;
import pl.sowol.voucherstore.sales.offer.OfferMaker;
import pl.sowol.voucherstore.sales.ordering.Reservation;
import pl.sowol.voucherstore.sales.payment.PaymentDetails;
import pl.sowol.voucherstore.sales.payment.PaymentGateway;
import pl.sowol.voucherstore.sales.payment.PaymentUpdateStatusRequest;
import pl.sowol.voucherstore.sales.payment.UntrustedPaymentExcpetion;

public class SalesFacade {

    private final OfferMaker offerMaker;
    private InMemoryBasketStorage basketStorage;
    private ProductCatalogFacade productCatalogFacade;
    private CurrentCustomerContext currentCustomerContext;
    private Inventory inventory;
    private PaymentGateway paymentGateway;

    public SalesFacade(InMemoryBasketStorage basketStorage, ProductCatalogFacade productCatalogFacade, CurrentCustomerContext currentCustomerContext, Inventory inventory, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        this.basketStorage = basketStorage;
        this.productCatalogFacade = productCatalogFacade;
        this.currentCustomerContext = currentCustomerContext;
        this.inventory = inventory;
        this.offerMaker = offerMaker;
        this.paymentGateway = paymentGateway;
    }

    public void addToBasket(String productId1) {
        Basket basket = basketStorage.loadForCustomer(currentCustomerContext.getCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalogFacade.getById(productId1);

        basket.add(product, inventory);

        basketStorage.addForCustomer(currentCustomerContext.getCustomerId(), basket);
    }

    public Offer getCurrentOffer() {
        Basket basket = basketStorage.loadForCustomer(currentCustomerContext.getCustomerId())
                .orElse(Basket.empty());

        return offerMaker.calculateOffer(basket.getProductsList());
    }

    public PaymentDetails acceptOffer(ClientDetails clientDetails, Offer seenOffer) {
        Basket basket = basketStorage.loadForCustomer(currentCustomerContext.getCustomerId())
                .orElse(Basket.empty());

        Offer currentOffer = offerMaker.calculateOffer(basket.getProductsList());

        if (!seenOffer.equals(currentOffer)) {
            throw new OfferChangeException();
        }

        Reservation reservation = Reservation.of(currentOffer, clientDetails);

        return paymentGateway.registerFor(reservation);
    }

    public void handlePaymentStatusChange(PaymentUpdateStatusRequest updateStatusRequest) {
        if (!paymentGateway.isTrusted(updateStatusRequest)) {
            throw new UntrustedPaymentExcpetion();
        }
    }
}

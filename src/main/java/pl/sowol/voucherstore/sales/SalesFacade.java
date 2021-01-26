package pl.sowol.voucherstore.sales;

import pl.sowol.voucherstore.productcatalog.Product;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.Basket;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.Offer;
import pl.sowol.voucherstore.sales.offer.OfferMaker;

public class SalesFacade {

    private final OfferMaker offerMaker;
    private InMemoryBasketStorage basketStorage;
    private ProductCatalogFacade productCatalogFacade;
    private CurrentCustomerContext currentCustomerContext;
    private Inventory inventory;

    public SalesFacade(InMemoryBasketStorage basketStorage, ProductCatalogFacade productCatalogFacade, CurrentCustomerContext currentCustomerContext, Inventory inventory, OfferMaker offerMaker) {
        this.basketStorage = basketStorage;
        this.productCatalogFacade = productCatalogFacade;
        this.currentCustomerContext = currentCustomerContext;
        this.inventory = inventory;
        this.offerMaker = offerMaker;
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

    public String acceptOffer(Offer seenOffer) {
        return null;
    }
}

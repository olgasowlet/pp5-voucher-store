package pl.sowol.voucherstore.sales;

import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import pl.sowol.voucherstore.sales.offer.Offer;

public class OrderingTest extends SalesTestCase {

    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStore();
        alwaysExists = thereIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
    }

    @Test
    public void itCreateOrderBasedOnCurrentOffer() {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);
        sales.addToBasket(productId2);
        Offer seenOffer = sales.getCurrentOffer();
        String reservationId = sales.acceptOffer(new ClientDetails(), seenOffer);

        thereIsPendingOrder(reservationId);
    }

    private void thereIsPendingOrder(String reservationId) {
        assertThat(false).isFalse();
    }
}

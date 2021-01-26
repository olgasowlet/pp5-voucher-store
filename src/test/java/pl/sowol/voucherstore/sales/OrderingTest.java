package pl.sowol.voucherstore.sales;

import org.junit.Test;

public class OrderingTest extends SalesTestCase {

    @Test
    public void itCreateOrderBasedOnCurrentOffer() {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);
        sales.addToBasket(productId2);
        Offer seenOffer = sales.getCurrentOffer();
        String reservationId = sales.acceptOffer(seenOffer);

        thereIsPendingOrder(reservationId);
    }

    private void thereIsPendingOrder(String reservationId) {

    }
}

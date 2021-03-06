package pl.sowol.voucherstore.sales;

import static org.assertj.core.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;
import pl.sowol.payment.payu.exceptions.PayUException;
import pl.sowol.voucherstore.sales.offer.Offer;
import pl.sowol.voucherstore.sales.ordering.ReservationRepository;
import pl.sowol.voucherstore.sales.payment.PaymentDetails;

public class OrderingTest extends SalesTestCase {

    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStore();
        alwaysExists = thereIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
        paymentGateway = thereIsInMemoryPaymentGateway();
        reservationRepository = thereIsInMemoryReservationRepository();
    }

    @Test
    public void itCreateOrderBasedOnCurrentOffer() throws PayUException {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);
        sales.addToBasket(productId2);
        Offer seenOffer = sales.getCurrentOffer();

        PaymentDetails paymentDetails;
        paymentDetails = sales.acceptOffer(new ClientDetails(), seenOffer);

        thereIsPendingReservationWithId(paymentDetails.getReservationId());
        thereIsPaymentRegisteredForReservation(paymentDetails.getReservationId());
    }

    private void thereIsPaymentRegisteredForReservation(String reservationId) {
        var reservation= reservationRepository.loadById(reservationId).get();
        assertThat(reservation.getPaymentId()).isNotNull();
    }

    private void thereIsPendingReservationWithId(String reservationId) {
        var reservation= reservationRepository.loadById(reservationId).get();
        assertThat(reservation.isPending()).isTrue();
        assertThat(reservation.isCompleated()).isFalse();
    }

}

package pl.sowol.voucherstore.sales.payment;

import pl.sowol.payment.payu.exceptions.PayUException;
import pl.sowol.voucherstore.sales.ordering.Reservation;

public interface PaymentGateway {
    PaymentDetails registerFor(Reservation reservation) throws PayUException;

    boolean isTrusted(PaymentUpdateStatusRequest updateStatusRequest);
}

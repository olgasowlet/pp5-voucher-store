package pl.sowol.voucherstore.sales.payment;

import pl.sowol.payment.payu.exceptions.PayUException;

public class PaymentException extends IllegalStateException {
    public PaymentException(PayUException e) {
        super(e);
    }
}

package pl.sowol.voucherstore.sales.exceptions;

public class NotEnoughQuantityException extends IllegalStateException {
    public NotEnoughQuantityException() {
        super("Not enough products on state");
    }
}

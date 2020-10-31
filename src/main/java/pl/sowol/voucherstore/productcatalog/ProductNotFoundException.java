package pl.sowol.voucherstore.productcatalog;

public class ProductNotFoundException extends IllegalAccessException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}

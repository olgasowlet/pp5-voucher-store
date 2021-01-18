package pl.sowol.voucherstore.sales;

public interface Inventory {
    boolean isAvailable(String productId);
}

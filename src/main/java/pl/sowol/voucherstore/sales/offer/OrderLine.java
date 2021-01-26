package pl.sowol.voucherstore.sales.offer;

import java.math.BigDecimal;

public class OrderLine {

    private String productId;
    private String description;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderLine(String productId, String description, BigDecimal unitPrice, int quantity) {

        this.productId = productId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}

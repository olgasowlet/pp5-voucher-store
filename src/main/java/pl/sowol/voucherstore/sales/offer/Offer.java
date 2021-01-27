package pl.sowol.voucherstore.sales.offer;

import java.math.BigDecimal;
import java.util.List;

public class Offer {
    private List<OrderLine> orderItems;
    private BigDecimal total;
    private Integer productCount;

    public Offer(List<OrderLine> orderItems, BigDecimal total) {

        this.orderItems = orderItems;
        this.total = total;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<OrderLine> getOrderItems() {
        return orderItems;
    }

    public boolean isEqual(Offer currentOffer) {
        return true;
    }

    public Integer getProductCount() {
        return productCount;
    }
}

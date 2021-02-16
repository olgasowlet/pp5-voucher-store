package pl.sowol.voucherstore.sales.ordering;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class ReservationItem {
    @Id
    private Integer id;

    @ManyToOne
    @JoinColumn(name="reservation_id")
    private Reservation reservation;

    private final String productId;
    private final String description;
    private final BigDecimal unitPrice;
    private final int quantity;

    public ReservationItem(String productId, String description, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.description = description;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getName() {
        return description;
    }

    public Integer getUnitPrice() {
        return unitPrice.intValue() * 100;
    }

    public Integer getQuantity() {
        return quantity;
    }
}

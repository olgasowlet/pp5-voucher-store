package pl.sowol.voucherstore.sales.product;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDetails {
    private String productId;
    private String description;
    private BigDecimal unitPrice;

//    public ProductDetails(String productId, String description, BigDecimal price) {
//
//    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}

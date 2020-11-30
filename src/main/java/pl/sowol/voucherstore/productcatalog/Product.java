package pl.sowol.voucherstore.productcatalog;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Product {
    @Id
    private String productID;
    private String description;
    private String picture;
    private BigDecimal price;

    public Product(UUID productId) {
        this.productID = productId.toString();
    }

    public String getId() {
        return productID;
    }

    private String newValue;

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPicture() {
        return picture;
    }

    public Product setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
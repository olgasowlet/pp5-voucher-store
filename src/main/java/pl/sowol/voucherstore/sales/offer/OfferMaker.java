package pl.sowol.voucherstore.sales.offer;

import pl.sowol.voucherstore.sales.basket.BasketItem;

import java.math.BigDecimal;
import java.util.List;

public class OfferMaker {

    public BigDecimal getTotal() { return BigDecimal.valueOf(100)}

    public Offer calculateOffer(List<BasketItem> basketItems) {
        return null;
    }
}

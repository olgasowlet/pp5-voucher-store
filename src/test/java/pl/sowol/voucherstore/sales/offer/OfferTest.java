package pl.sowol.voucherstore.sales.offer;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import pl.sowol.voucherstore.sales.basket.BasketItem;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class OfferTest {

    @Test
    public void itCreateOfferBasedOnBasketItems() {
        List<BasketItem> basketItems = thereCollectedItems();
        OfferMaker offerMaker = thereIsOfferMaker();

        Offer offer = offerMaker.calculateOffer(basketItems);

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(100));
    }

    private List<BasketItem> thereCollectedItems() {
        return Arrays.asList(
                new BasketItem("prod1", 2);
                new BasketItem("prod2", 1);
        )
    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker();
    }
}

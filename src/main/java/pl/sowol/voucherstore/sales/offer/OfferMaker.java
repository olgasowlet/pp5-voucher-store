package pl.sowol.voucherstore.sales.offer;

import pl.sowol.voucherstore.sales.basket.BasketItem;
import pl.sowol.voucherstore.sales.product.ProductDetails;
import pl.sowol.voucherstore.sales.product.ProductDetailsProvider;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class OfferMaker {

    ProductDetailsProvider productDetailsProvider;

    public OfferMaker(ProductDetailsProvider productDetailsProvider) {
        this.productDetailsProvider = productDetailsProvider;
    }

    public Offer calculateOffer(List<BasketItem> basketItems) {
        List<OrderLine> orderItems = basketItems.stream()
                .map(this::createOrderLine)
                .collect(Collectors.toUnmodifiableList());

        return new Offer(orderItems, calculateTotal(orderItems));
    }

    private OrderLine createOrderLine(BasketItem basketItem) {
        ProductDetails details = productDetailsProvider.getByProductId(basketItem.getProductId());

        return new OrderLine(
                basketItem.getProductId(),
                details.getDescription(),
                details.getUnitPrice(),
                basketItem.getQuantity());
    }

    private BigDecimal calculateTotal(List<OrderLine> orderItems) {
        return orderItems.stream()
                .map(orderLine -> orderLine.getUnitPrice().multiply(new BigDecimal(orderLine.getQuantity())))
                .reduce((acc, lineTotal) -> acc.add(lineTotal))
                .orElse(BigDecimal.ZERO);
    }


}

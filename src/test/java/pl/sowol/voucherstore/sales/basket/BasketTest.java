package pl.sowol.voucherstore.sales.basket;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import pl.sowol.voucherstore.productcatalog.Product;
import pl.sowol.voucherstore.sales.Inventory;
import pl.sowol.voucherstore.sales.basket.Basket;
import pl.sowol.voucherstore.sales.basket.BasketItem;

import java.util.UUID;

public class BasketTest {
    @Test
    public void createdBasketIsEmpty() {
        Basket basket = Basket.empty();

        assertThat(basket.isEmpty())
                .isTrue();
    }

    @Test
    public void basketWithProductIsNoEmpty() {
        Basket basket = Basket.empty();
        Product product = thereIsProduct("lego");

        basket.add(product, thereIsInventory());

        assertThat(basket.isEmpty())
                .isFalse();
    }

    @Test
    public void itShowsProductsCount() {
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct("lego");
        Product product2 = thereIsProduct("lego");

        basket.add(product1, thereIsInventory());
        basket.add(product2, thereIsInventory());

        assertThat(basket.getProductsCount())
                .isEqualTo(2);
    }

    @Test
    public void itShowsSingleLineWhenSameProductsAddedTwice() {
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct("lego");

        basket.add(product1, thereIsInventory());
        basket.add(product1, thereIsInventory());

        assertThat(basket.getProductsCount())
                .isEqualTo(1);

        basketContainsProductWithQuantity(basket, product1, 2);
    }

    @Test
    public void itStoresQuantityOfMultipleProducts() {
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct("lego");
        Product product2 = thereIsProduct("lego");

        basket.add(product1, thereIsInventory());
        basket.add(product1, thereIsInventory());
        basket.add(product2, thereIsInventory());

        assertThat(basket.getProductsCount())
                .isEqualTo(2);

        basketContainsProductWithQuantity(basket, product1, 2);
        basketContainsProductWithQuantity(basket, product2, 1);
    }

    @Test
    public void itDeniesToAddProductWithInventoryStateOfZero() {
        Basket basket = Basket.empty();
        Product product1 = thereIsProduct("lego");
//        AlwaysTrueInventory inventory = thereIsInventory();

        thereIsInventoryStateForProduct(product1.getId(), 0);
        assertThatThrownBy(() -> basket.add(product1, (productId) -> false))
                .hasMessage("Not enough products on state");

    }

    private void thereIsInventoryStateForProduct(String id, int i) {
    }

    private Inventory thereIsInventory() {
        return (productId -> true);
    }

    private void basketContainsProductWithQuantity(Basket basket, Product product1, int expectedQuantity) {
        assertThat(basket.getProductsList())
                .filteredOn(basketItem -> basketItem.getProductId().equals(product1.getId()))
                .extracting(BasketItem::getQuantity)
                .first()
                .isEqualTo(expectedQuantity);
    }

    private Product thereIsProduct(String descripton) {
        var product = new Product(UUID.randomUUID());
        product.setDescription(descripton);

        return product;
    }
}

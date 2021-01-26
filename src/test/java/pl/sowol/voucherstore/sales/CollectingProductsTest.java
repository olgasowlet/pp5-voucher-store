package pl.sowol.voucherstore.sales;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import pl.sowol.voucherstore.sales.basket.Basket;
import pl.sowol.voucherstore.sales.offer.Offer;
import pl.sowol.voucherstore.sales.offer.OfferMaker;

import java.math.BigDecimal;

public class CollectingProductsTest extends SalesTestCase {

    @Before
    public void setUp() {
        productCatalog = thereIsProductCatalog();
        basketStorage = thereIsBasketStore();
        alwaysExists = thereIsInventory();
        currentCustomerContext = thereIsCurrentCustomerContext();
        offerMaker = thereIsOfferMaker(productCatalog);
    }

    @Test
    public void itAllowsAddProductToBasket() {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);
        sales.addToBasket(productId2);

        thereIsXProductsInCustomerBasket(2, customerId);
    }

    @Test
    public void itAllowsAddProductToBasketByMultipleCustomers() {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();
        var customer1 = new String(customerId);
        sales.addToBasket(productId1);
        sales.addToBasket(productId2);

        customerId = thereIsCustomerWhoIsDoingSomeShopping();
        var customer2 = new String(customerId);
        sales.addToBasket(productId1);

        thereIsXProductsInCustomerBasket(2, customer1);
        thereIsXProductsInCustomerBasket(1, customer2);
    }

    @Test
    public void itGenerateOfferBasedOnCurrentBasket(){
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();

        customerId = thereIsCustomerWhoIsDoingSomeShopping();
        var customer1 = new String(customerId);

        sales.addToBasket(productId1);
        sales.addToBasket(productId1);
        sales.addToBasket(productId2);

        Offer offer = sales.getCurrentOffer();

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(30));
    }

    @Test
    public void itGenerateOfferBasedOnCurrentBasketWithOneProduct(){
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();

        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);

        Offer offer = sales.getCurrentOffer();

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(10));
    }

    private void thereIsXProductsInCustomerBasket(int productsCount, String customerId) {
        Basket basket = basketStorage.loadForCustomer(customerId)
                .orElse(Basket.empty());
        assertThat(basket.getProductsCount()).isEqualTo(productsCount);
    }
}

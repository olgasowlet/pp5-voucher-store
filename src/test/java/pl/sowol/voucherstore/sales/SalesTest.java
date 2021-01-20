package pl.sowol.voucherstore.sales;
import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;
import pl.sowol.voucherstore.productcatalog.ProductCatalogConfiguration;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;

import java.math.BigDecimal;
import java.util.UUID;

public class SalesTest {

    private ProductCatalogFacade productCatalog;
    private InMemoryBasketStorage basketStorage;
    private Inventory alwaysExists;
    private CurrentCustomerContext currentCustomerContext;
    private String customerId;

    @Before
    public void setUp() {
        productCatalog = new ProductCatalogConfiguration().productCatalogFacade();
        basketStorage = new InMemoryBasketStorage();
        alwaysExists = (productId -> true);
        currentCustomerContext = () -> customerId;
    }

    @Test
    public void itAllowsAddProductToBasket() {
        SalesFacade sales = thereIsSalesModule();
        String productId1 = thereIsProductAvaiable();
        String productId2 = thereIsProductAvaiable();
        customerId = thereIsCustomerWhoIsDoingSomeShopping();

        sales.addToBasket(productId1);
        sales.addToBasket(productId2);

        thereIsXProductsInCustomerBusket(2, customerId);
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

        thereIsXProductsInCustomerBusket(2, customer1);
        thereIsXProductsInCustomerBusket(1, customer2);
    }

    private String thereIsCustomerWhoIsDoingSomeShopping() {
        return UUID.randomUUID().toString();
    }

    private String thereIsProductAvaiable() {
        var id = productCatalog.createProduct();
        productCatalog.applyPrice(id, BigDecimal.valueOf(10));
        productCatalog.updateDetails(id, "lego", "http://picture");

        return id;
    }

    private SalesFacade thereIsSalesModule() {
        return new SalesFacade(basketStorage, productCatalog, currentCustomerContext, alwaysExists);
    }

    private void thereIsXProductsInCustomerBusket(int productsCount, String customerId) {
        Basket basket = basketStorage.loadForCustomer(customerId)
                .orElse(Basket.empty());
        assertThat(basket.getProductsCount()).isEqualTo(productsCount);
    }
}

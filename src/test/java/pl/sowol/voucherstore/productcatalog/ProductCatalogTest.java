package pl.sowol.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ProductCatalogTest {

    public static final String MY_PRODUCT_DESC = "my product";
    public static final String MY_PRODUCT_PICTURE = "http://myproduct/image";

    @Test
    public void itAllowCreateProduct() throws ProductNotFoundException {
        //Arange
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        //Act
        String productId = productCatalog.createProduct();
        //Assert
        Assert.assertTrue((productCatalog.getById(productId)).getId().equals(productId));
        Assert.assertTrue(productCatalog.isExistById(productId));

    }

    @Test
    public void itAllowsSetProductDescription() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String productId = productCatalog.createProduct();

        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_PICTURE);
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertEquals( MY_PRODUCT_DESC ,loadedProduct.getDescription());
        Assert.assertEquals( MY_PRODUCT_PICTURE ,loadedProduct.getPicture());

    }

    @Test
    public void itAllowsApplyPrice() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String productId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertTrue( BigDecimal.valueOf(20.20).equals(loadedProduct.getPrice()));
    }

    @Test
    public void itAllowsLoadAllProducts() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String draftProductId = productCatalog.createProduct();
        String productId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_PICTURE);

        List<Product> all = productCatalog.getAllAvaiableProducts();
        assertThat(all)
                .hasSize(1)
                .extracting(Product::getId)
                .contains(productId)
                .doesNotContain((draftProductId));
    }

    @Test
    public void itDenyActionOnProductThatNotExist() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        try {
            productCatalog.applyPrice("notExist", BigDecimal.TEN);
            Assert.fail("should throw exception");
        } catch (ProductNotFoundException e) {
            Assert.assertTrue(true);
        }

    }

    @Test(expected = ProductNotFoundException.class)
    public void itDenyActionOnProductThatNotExistV2() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        productCatalog.applyPrice("notExists", BigDecimal.TEN);
        productCatalog.updateDetails("notExists", "desc", "url");

    }

    @Test
    public void itDenyActionOnProductThatNotExistV3() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        assertThatThrownBy(() -> productCatalog.applyPrice("NotExists", BigDecimal.TEN))
                .hasMessage("There is no product with id: NotExists");


    }

    @Test
    public void exceptionOnLoadindNotExisted() throws ProductNotFoundException {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();

        assertThatThrownBy(() -> productCatalog.applyPrice("NotExists", BigDecimal.TEN))
                .hasMessage("There is no product with id: NotExists");

        assertThatThrownBy(() -> productCatalog.getById("NotExists"))
                .hasMessage("There is no product with id: NotExists");


    }


    private ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }
}

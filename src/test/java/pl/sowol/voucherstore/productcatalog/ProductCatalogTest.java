package pl.sowol.voucherstore.productcatalog;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class ProductCatalogTest {

    public static final String MY_PRODUCT_DESC = "my product";
    public static final String MY_PRODUCT_PICTURE = "http://myproduct/image";

    @Test
    public void itAllowCreateProduct() {
        //Arange
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        //Act
        String productId = productCatalog.createProduct();
        //Assert
        Assert.assertTrue((productCatalog.getById(productId)).getId().equals(productId));
        Assert.assertTrue(productCatalog.isExistById(productId));

    }

    @Test
    public void itAllowsSetProductDescription() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String productId = productCatalog.createProduct();

        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_PICTURE);
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertEquals( MY_PRODUCT_DESC ,loadedProduct.getDescription());
        Assert.assertEquals( MY_PRODUCT_PICTURE ,loadedProduct.getPicture());

    }

    @Test
    public void itAllowsApplyPrice() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String productId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        Product loadedProduct = productCatalog.getById(productId);

        Assert.assertTrue( BigDecimal.valueOf(20.20).equals(loadedProduct.getPrice()));
    }

    @Test
    public void itAllowsLoadAllProducts() {
        ProductCatalogFacade productCatalog = thereIsProductCatalog();
        String draftProductId = productCatalog.createProduct();

        productCatalog.applyPrice(productId, BigDecimal.valueOf(20.20));
        productCatalog.updateDetails(productId, MY_PRODUCT_DESC, MY_PRODUCT_PICTURE);

        List<Product> all = productCatalog.getAllAvaiableProducts();
        Assert.assertEquals(1, all.size());
    }


    private ProductCatalogFacade thereIsProductCatalog() {
        return new ProductCatalogConfiguration().productCatalogFacade();
    }
}

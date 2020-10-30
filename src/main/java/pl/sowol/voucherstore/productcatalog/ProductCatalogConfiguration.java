package pl.sowol.voucherstore.productcatalog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ProductCatalogConfiguration {

    public ProductCatalogFacade productCatalogFacade() {
        return new ProductCatalogFacade();
    }

    @Bean
    public ProductCatalogFacade fixturesAwareProductCatalogFacade() {
        ProductCatalogFacade catalogFacade = new ProductCatalogFacade();

        String pId1 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId1, BigDecimal.TEN);
        catalogFacade.updateDetails(pId1, "my product 1", "my product url");

        String pId2 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId2, BigDecimal.TEN);
        catalogFacade.updateDetails(pId2, "my product 2", "my product url");

        String pId3 = catalogFacade.createProduct();
        catalogFacade.applyPrice(pId3, BigDecimal.TEN);
        catalogFacade.updateDetails(pId3, "my product 3", "my product url");
        return catalogFacade;
    }
}

package pl.sowol.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.OfferMaker;
import pl.sowol.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.sowol.voucherstore.sales.product.ProductDetails;
import pl.sowol.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker) {
        return new SalesFacade(
                new InMemoryBasketStorage(),
                productCatalogFacade,
                        () -> "customer1",
                        (productId) -> true,
                        offerMaker

                );
    }

    @Bean
    OfferMaker offerMaker(ProductDetailsProvider productDetailsProvider) {
        return new OfferMaker(productDetailsProvider);
    }

    @Bean
    ProductDetailsProvider productDetailsProvider(ProductCatalogFacade productCatalogFacade){
        return new ProductCatalogProductDetailsProvider(productCatalogFacade);
    }

}

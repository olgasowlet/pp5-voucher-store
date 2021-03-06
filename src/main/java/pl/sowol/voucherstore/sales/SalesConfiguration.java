package pl.sowol.voucherstore.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.sowol.payment.payu.PayU;
import pl.sowol.payment.payu.PayUApiConfiguration;
import pl.sowol.payment.payu.http.HttpClientPayU;
import pl.sowol.voucherstore.productcatalog.ProductCatalogFacade;
import pl.sowol.voucherstore.sales.basket.InMemoryBasketStorage;
import pl.sowol.voucherstore.sales.offer.OfferMaker;
import pl.sowol.voucherstore.sales.ordering.ReservationRepository;
import pl.sowol.voucherstore.sales.payment.PayUPaymentGateway;
import pl.sowol.voucherstore.sales.payment.PaymentGateway;
import pl.sowol.voucherstore.sales.product.ProductCatalogProductDetailsProvider;
import pl.sowol.voucherstore.sales.product.ProductDetailsProvider;

@Configuration
public class SalesConfiguration {

    @Bean
    SalesFacade salesFacade(ProductCatalogFacade productCatalogFacade, OfferMaker offerMaker, PaymentGateway paymentGateway, ReservationRepository reservationRepository) {
        return new SalesFacade(
                new InMemoryBasketStorage(),
                productCatalogFacade,
                () -> "customer1",
                (productId) -> true,
                offerMaker,
                paymentGateway,
                reservationRepository
                );
    }

    @Bean
    PaymentGateway payUPaymentGateway(PayU payU) {
        return new PayUPaymentGateway(payU);
    }

    @Bean
    PayU payU() {
        return new PayU(
                PayUApiConfiguration.sandbox(),
                new HttpClientPayU()
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

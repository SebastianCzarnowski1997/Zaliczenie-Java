package pl.jkanclerz.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jkanclerz.vouchershop.catalog.ProductCatalog;
import pl.jkanclerz.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.jkanclerz.vouchershop.sales.offering.OfferMaker;
import pl.jkanclerz.vouchershop.sales.offering.ProductCatalogPricingProvider;


@Configuration
public class SalesConfiguration {
    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog, OfferMaker offerMaker, PaymentGateway paymentGateway) {
        return new SalesFacade(customerContext, new InMemoryBasketStorage(), productCatalog, offerMaker, paymentGateway);
    }

    @Bean
    PaymentGateway paymentGateway() {
        return null;
    }

    @Bean
    CurrentCustomerContext customerContext() {
        return new AlwaysTheSameCustomerContext();
    }

    @Bean
    OfferMaker offerMaker(ProductCatalog productCatalog) {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}

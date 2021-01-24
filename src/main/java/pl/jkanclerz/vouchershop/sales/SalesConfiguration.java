package pl.jkanclerz.vouchershop.sales;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.jkanclerz.vouchershop.catalog.ProductCatalog;
import pl.jkanclerz.vouchershop.sales.basket.InMemoryBasketStorage;


@Configuration
public class SalesConfiguration {
    @Bean
    SalesFacade salesFacade(CurrentCustomerContext customerContext, ProductCatalog productCatalog) {
        return new SalesFacade(customerContext, new InMemoryBasketStorage(), productCatalog);
    }

    @Bean
    CurrentCustomerContext customerContext() {
        return new RandomCustomerContext();
    }
}

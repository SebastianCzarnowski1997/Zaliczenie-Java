package pl.jkanclerz.vouchershop.sales;

import org.junit.Before;
import org.junit.Test;
import pl.jkanclerz.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.jkanclerz.vouchershop.sales.offering.Offer;
import pl.jkanclerz.vouchershop.sales.offering.OfferMaker;
import pl.jkanclerz.vouchershop.sales.offering.ProductCatalogPricingProvider;

import java.math.BigDecimal;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderingTest extends SalesTestCase {
    @Before
    public void setUp() {
        this.basketStorage = new InMemoryBasketStorage();
        this.customerId = UUID.randomUUID().toString();
        this.userContext = () -> customerId;
        this.productCatalog = thereIsProductCatalog();
        this.offerMaker = thereIsOfferMaker();
    }

    @Test
    public void itCreateOfferBasedSelectedProducts() {
        SalesFacade salesModule = thereIsSalesModule();
        String productId = thereIsProductAvailable();
        String customerId = thereIsCustomerWhoIsDoingSomeShoping();

        salesModule.addToBasket(productId);
        salesModule.addToBasket(productId);

        Offer offer = salesModule.getCurrentOffer();

        assertThat(offer.getTotal()).isEqualTo(BigDecimal.valueOf(40.40));
    }

    private OfferMaker thereIsOfferMaker() {
        return new OfferMaker(new ProductCatalogPricingProvider(productCatalog));
    }
}

package pl.jkanclerz.vouchershop.sales;

import pl.jkanclerz.vouchershop.catalog.Product;
import pl.jkanclerz.vouchershop.catalog.ProductCatalog;
import pl.jkanclerz.vouchershop.sales.basket.Basket;
import pl.jkanclerz.vouchershop.sales.basket.InMemoryBasketStorage;
import pl.jkanclerz.vouchershop.sales.offering.Offer;
import pl.jkanclerz.vouchershop.sales.offering.OfferMaker;

public class SalesFacade {

    private final CurrentCustomerContext currentSystemUserContext;
    private final InMemoryBasketStorage basketStorage;
    private final ProductCatalog productCatalog;
    private final OfferMaker offerMaker;

    public SalesFacade(CurrentCustomerContext currentSystemUserContext, InMemoryBasketStorage basketStorage, ProductCatalog productCatalog, OfferMaker offerMaker) {
        this.currentSystemUserContext = currentSystemUserContext;
        this.basketStorage = basketStorage;
        this.productCatalog = productCatalog;
        this.offerMaker = offerMaker;
    }

    public void addToBasket(String productId) {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        Product product = productCatalog.load(productId);

        basket.add(product);

        basketStorage.addForCustomer(getCurrentCustomerId(), basket);
    }

    private String getCurrentCustomerId() {
        return currentSystemUserContext.getCustomerId();
    }

    public Offer getCurrentOffer() {
        Basket basket = basketStorage.getBasket(getCurrentCustomerId())
                .orElse(Basket.empty());

        return offerMaker.calculate(basket.getBasketItems());
    }

    public PaymentDetails acceptOffer() {
        return null;
    }
}

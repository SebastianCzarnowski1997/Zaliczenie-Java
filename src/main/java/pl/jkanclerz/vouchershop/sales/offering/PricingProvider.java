package pl.jkanclerz.vouchershop.sales.offering;

public interface PricingProvider {
    ProductPricing getForProduct(String productId);
}

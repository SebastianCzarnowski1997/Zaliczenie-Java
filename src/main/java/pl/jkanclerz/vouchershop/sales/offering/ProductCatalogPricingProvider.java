package pl.jkanclerz.vouchershop.sales.offering;

import pl.jkanclerz.vouchershop.catalog.Product;
import pl.jkanclerz.vouchershop.catalog.ProductCatalog;
import pl.jkanclerz.vouchershop.sales.offering.PricingProvider;
import pl.jkanclerz.vouchershop.sales.offering.ProductPricing;

public class ProductCatalogPricingProvider implements PricingProvider {
    private final ProductCatalog productCatalog;

    public ProductCatalogPricingProvider(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public ProductPricing getForProduct(String productId) {
        Product product = productCatalog.load(productId);
        return new ProductPricing(product.getPrice(), product.getDescription());
    }
}

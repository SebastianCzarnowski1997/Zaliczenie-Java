package pl.jkanclerz.vouchershop.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.jkanclerz.vouchershop.sales.offering.Offer;

@RestController
public class SalesController {

    private final SalesFacade salesFacade;

    public SalesController(SalesFacade salesFacade) {
        this.salesFacade = salesFacade;
    }

    @PostMapping("/api/basket/add/{productId}")
    public void addToBasket(@PathVariable String productId) {
        salesFacade.addToBasket(productId);
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return salesFacade.getCurrentOffer();
    }

    @PostMapping("/api/accept-offer")
    public PaymentDetails acceptOffer() {
        return salesFacade.acceptOffer();
    }
}

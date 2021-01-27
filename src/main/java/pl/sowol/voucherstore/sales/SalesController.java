package pl.sowol.voucherstore.sales;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sowol.voucherstore.sales.offer.Offer;

@RestController
public class SalesController {

    private final SalesFacade sales;

    public SalesController(SalesFacade salesFacade){
        this.sales = salesFacade;
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return sales.getCurrentOffer();
    }

    @PostMapping("/api/add-product/{productId}")
    public void addProductToBasket(@PathVariable String productId) {
        sales.addToBasket(productId);
    }

}

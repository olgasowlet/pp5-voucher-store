package pl.sowol.voucherstore.sales;

import org.springframework.web.bind.annotation.*;
import pl.sowol.voucherstore.sales.dto.AcceptOfferRequest;
import pl.sowol.voucherstore.sales.offer.Offer;
import pl.sowol.voucherstore.sales.payment.PaymentUpdateStatusRequest;

@RestController
public class SalesController {

    private final SalesFacade sales;

    public SalesController(SalesFacade sales){
        this.sales = sales;
    }

    @GetMapping("/api/current-offer")
    public Offer currentOffer() {
        return sales.getCurrentOffer();
    }

    @PostMapping("/api/add-product/{productId}")
    public void addProductToBasket(@PathVariable String productId) {
        sales.addToBasket(productId);
    }

    @PostMapping("/api/accept-offer")
    public void acceptOffer(@RequestBody AcceptOfferRequest acceptOfferRequest) {
        sales.acceptOffer(acceptOfferRequest.getClientDetails(), acceptOfferRequest.getSeenOffer());
    }

    @PostMapping("api/payment/status")
    public void updatePaymentStatus(@RequestHeader("OpenPayu-Signature") String signatureHeader, @RequestBody String body) {
        PaymentUpdateStatusRequest updateStatusRequest = PaymentUpdateStatusRequest.of(signatureHeader, body);
        sales.handlePaymentStatusChange(updateStatusRequest);
    }

}

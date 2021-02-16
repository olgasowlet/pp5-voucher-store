package pl.sowol.voucherstore.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sowol.voucherstore.sales.ClientDetails;
import pl.sowol.voucherstore.sales.offer.Offer;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AcceptOfferRequest {
    @NotNull
    private Offer seenOffer;
    @NotNull
    private ClientDetails clientDetails;

    public ClientDetails getClientDetails() {
        return null;
    }

    public Offer getSeenOffer() {
        return null;
    }
}

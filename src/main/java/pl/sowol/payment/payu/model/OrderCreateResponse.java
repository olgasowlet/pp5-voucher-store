package pl.sowol.payment.payu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateResponse {
    private String redirectUri;
    private String orderId;
    private String extOrderId;
}

package pl.sowol.payment.payu.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("expires_in")
    private Integer expiresIn;
    @JsonProperty("grant_type")
    private String grantType;

    /*
        "access_token":"7524f96e-2d22-45da-bc64-778a61cbfc26",
    "token_type":"bearer",
    "expires_in":43199, //expiration time in seconds
    "grant_type":"client_credentials"
    */
}

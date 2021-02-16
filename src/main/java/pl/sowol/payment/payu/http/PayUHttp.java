package pl.sowol.payment.payu.http;

import pl.sowol.payment.payu.exceptions.PayUException;

import java.net.http.HttpResponse;
import java.util.Map;

public interface PayUHttp {
    HttpResponse<String> post(String url, String body, Map<String, String> headers) throws PayUException;
}

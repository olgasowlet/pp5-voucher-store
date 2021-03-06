package pl.sowol.payment.payu.http;

import pl.sowol.payment.payu.exceptions.PayUException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;

public class HttpClientPayU implements PayUHttp {

    private HttpClient http;

    public HttpClientPayU() {
        this.http = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(20))
            .build()
        ;
    }

    @Override
    public HttpResponse<String> post(String url, String body, Map<String, String> headers) throws PayUException {
        var httpRequestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(body));

        headers.forEach(httpRequestBuilder::header);

        try {
            HttpResponse<String> r = http.send(httpRequestBuilder.build(), HttpResponse.BodyHandlers.ofString());
            return r;
        } catch (Exception e) {
            throw new PayUException(e);
        }
    }
}
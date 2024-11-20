package smartphone.input.service.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import smartphone.configuration.RestTemplateConfig;
import smartphone.exception.NoSuchResourceFoundException;

import java.util.Optional;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
public class SmartPhoneServiceRestClient {

    @Value("${smartphone.service.url}")
    private String smartphoneServiceUrl;
    private final RestTemplate restClient;

    public static final String CONTENT_TYPE = "application/json";


    public SmartPhoneServiceRestClient(RestTemplateConfig restTemplateConfig) {
        this.restClient = restTemplateConfig.restTemplate();
    }

    public <T> Optional<T> executeHTTPRequest(String urlSuffix, Object requestData, Class<T> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
        String url = smartphoneServiceUrl.concat(urlSuffix);

        try {
            ResponseEntity<T> httpResponse = restClient.exchange(
                    url,
                    GET,
                    new HttpEntity<>(headers),
                    clazz,
                    requestData
            );

            T responseBody = httpResponse.getBody();
            if (responseBody == null) {
                throw new NoSuchResourceFoundException("Returned empty response for URL: " + url);
            }

            return Optional.of(responseBody);

        } catch (HttpClientErrorException.NotFound e) {
            log.error("Resource not found for Id: {}. Error: {}", requestData, e.getMessage());
            return Optional.empty();
        } catch (HttpClientErrorException clientErrorException) {
            log.error("HTTP error for id: {}. Error: {}", requestData, clientErrorException.getMessage());
            throw clientErrorException;
        } catch (Exception e) {
            log.error("Unexpected error for id: {}. Error: {}", requestData, e.getMessage());
            throw new RuntimeException("Unexpected error during HTTP request", e);
        }
    }
}
package smartphone.input.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smartphone.input.model.SimilarSmartPhone;
import smartphone.input.service.client.SmartPhoneServiceRestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SimilarSmartPhoneService implements ISimilarSmartPhoneService{

    private static final String SIMILAR_PRICES_URL_SUFFIX = "/{id}/similarpricedids";
    private static final String SMARTPHONE_DETAILS_URL_SUFFIX = "/{id}";

    private final SmartPhoneServiceRestClient serviceRestClient;

    @Override
    public List<SimilarSmartPhone> getSimilarSmartphones(String id) {

        return getSimilarPricedIds(id).stream()
                .map(this::getSmartphoneDetails)
                .filter(Objects::nonNull)
                .toList();

    }

    private List<String> getSimilarPricedIds(String id) {
        return serviceRestClient.executeHTTPRequest(SIMILAR_PRICES_URL_SUFFIX, id, String.class)
                .map(response ->
                        Arrays.stream(response.replaceAll("[\\[\\]]", "").split(","))
                                .map(String::trim)
                                .toList()
                )
                .orElseGet(Collections::emptyList);
    }


    private SimilarSmartPhone getSmartphoneDetails(String id) {
        return serviceRestClient.executeHTTPRequest(SMARTPHONE_DETAILS_URL_SUFFIX, id, SimilarSmartPhone.class).orElse(null);
    }
}

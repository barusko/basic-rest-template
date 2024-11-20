package smartphone.controller;

import org.mockito.Mock;
import smartphone.input.controller.SimilarPricedController;
import smartphone.input.model.SimilarSmartPhone;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import smartphone.input.service.ISimilarSmartPhoneService;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class SimilarPricedControllerTest {

    @InjectMocks
    private SimilarPricedController similarPricedController;

    @Mock
    private ISimilarSmartPhoneService similarSmartPhoneServiceImpl;
    
    private static final String PHONE_ID = "1";

    @Test
     void when_callGetSimilarSmartphonesById_then_returnOKStatus() {

        var phone_one = SimilarSmartPhone.builder()
                .id("1")
                .name("Galaxy S23 Ultra")
                .brand("Samsung")
                .price("999.99")
                .build();

        var phone_two = SimilarSmartPhone.builder()
                .id("2")
                .name("12 pro")
                .brand("Xiaomi")
                .price("1099.99")
                .build();

        var expectedResponse  = List.of(phone_one, phone_two);

        when(similarSmartPhoneServiceImpl.getSimilarSmartphones(any())).thenReturn(expectedResponse);
        ResponseEntity<List<SimilarSmartPhone>> responseEntity = similarPricedController.findSimilarSmartphones(PHONE_ID);
        Assertions.assertThat(HttpStatus.OK).isEqualTo( responseEntity.getStatusCode());
        Assertions.assertThat(expectedResponse).isEqualTo(responseEntity.getBody());
    }
}

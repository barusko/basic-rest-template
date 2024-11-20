package smartphone.input.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import smartphone.input.model.SimilarSmartPhone;
import smartphone.input.service.ISimilarSmartPhoneService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/smartphone", produces = "application/vnd.api.v1+json")
public class SimilarPricedController {

    private final ISimilarSmartPhoneService similarSmartPhoneServiceImpl;

    public SimilarPricedController(ISimilarSmartPhoneService similarSmartPhoneServiceImpl) {
        this.similarSmartPhoneServiceImpl = similarSmartPhoneServiceImpl;
    }


    @GetMapping("/{id}/similar")
    public ResponseEntity<List<SimilarSmartPhone>> findSimilarSmartphones(@PathVariable String id) {
        log.info("Checking for similar Smartphones with Id: {}", id);
        return ResponseEntity.ok(similarSmartPhoneServiceImpl.getSimilarSmartphones(id));
    }

}

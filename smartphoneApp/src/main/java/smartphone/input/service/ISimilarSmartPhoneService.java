package smartphone.input.service;

import smartphone.input.model.SimilarSmartPhone;

import java.util.List;

public interface ISimilarSmartPhoneService {

    List<SimilarSmartPhone> getSimilarSmartphones(String id);

}

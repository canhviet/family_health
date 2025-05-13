package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.AllergyRequest;
import sgu.j2ee.dto.response.AllergyResponse;

public interface AllergyService {
    Long add(AllergyRequest request);
    List<AllergyResponse> getByUserId(Long userId);
}

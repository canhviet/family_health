package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.ImmunizationRequest;
import sgu.j2ee.dto.response.ImmunizationResponse;

public interface ImmunizationService {
    Long add(ImmunizationRequest request);
    List<ImmunizationResponse> getByUserId(Long userId);
}

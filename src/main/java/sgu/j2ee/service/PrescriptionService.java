package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.PrescriptionRequest;
import sgu.j2ee.dto.response.PresciptionResponse;

public interface PrescriptionService {
    Long savePrescription(PrescriptionRequest request);
    List<PresciptionResponse> getByUserId(Long userId);
}

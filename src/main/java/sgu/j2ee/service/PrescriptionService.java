package sgu.j2ee.service;

import sgu.j2ee.dto.request.PrescriptionRequest;

public interface PrescriptionService {
    Long savePrescription(PrescriptionRequest request);
    
}

package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.HistoryRequest;
import sgu.j2ee.dto.response.HistoryResponse;

public interface MedicalHistoryService {
    List<HistoryResponse> listByUser(Long userId);
    Long add(HistoryRequest request);
    HistoryResponse nearestRevisitDate(Long userId);
}

package sgu.j2ee.service;

import java.util.List;

import sgu.j2ee.dto.request.DocumentRequest;
import sgu.j2ee.dto.response.DocumentResponse;

public interface DocumentService {
    Long addDocument(DocumentRequest request);
    List<DocumentResponse> getByUserId(Long userId);
}

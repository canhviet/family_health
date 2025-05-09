package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.DocumentRequest;
import sgu.j2ee.dto.response.DocumentResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.Documents;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.DocumentRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.DocumentService;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;

    private User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
    }

    @Override
    public Long addDocument(DocumentRequest request) {
        Documents document = Documents.builder()
                            .documentName(request.getDocumentName())
                            .documentUrl(request.getDocumentUrl())
                            .user(getUserById(request.getUserId()))
                            .build();
        documentRepository.save(document);

        return document.getDocumentId();

    }

    @Override
    public List<DocumentResponse> getByUserId(Long userId) {
        List<Documents> documents = documentRepository.findByUserId(userId);

        return documents.stream().map(doc -> DocumentResponse.builder()
                                        .documentId(doc.getDocumentId())
                                        .documentName(doc.getDocumentName())
                                        .documentUrl(doc.getDocumentUrl())
                                        .userId(userId)
                                        .uploadDate(doc.getUploadDate())
                                        .build()
        ).toList();
    }

}

package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sgu.j2ee.dto.request.DocumentRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.DocumentService;

@RestController
@RequestMapping("/api/document")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/")
    public ResponseData<?> addDocument(@RequestBody DocumentRequest request) {
        return new ResponseData<>(HttpStatus.OK.value(), "add new document", this.documentService.addDocument(request));
    }

    @GetMapping("/user/{userId}")
    public ResponseData<?> getByUserId(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "view by user", this.documentService.getByUserId(userId));
    }
}

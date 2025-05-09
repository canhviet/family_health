package sgu.j2ee.dto.request;

import lombok.Getter;

@Getter
public class DocumentRequest {
    String documentUrl;
    String documentName;
    Long userId;
}

package sgu.j2ee.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DocumentResponse {
    String documentUrl;
    String documentName;
    Long userId;
    Date uploadDate;
    Long documentId;
}

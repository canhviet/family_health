// TestResultsResponse.java
package sgu.j2ee.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TestResultsResponse {
    private Long testId;
    private Long userId;
    private String testType;
    private String result;
    private LocalDate datePerformed;
    private String labName;
}
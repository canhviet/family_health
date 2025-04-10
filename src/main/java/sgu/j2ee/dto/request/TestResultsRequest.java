// TestResultsRequest.java
package sgu.j2ee.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestResultsRequest {
    private Long userId;
    private String testType;
    private String result;
    private LocalDate datePerformed;
    private String labName;
}
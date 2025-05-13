// TestResultsResponse.java
package sgu.j2ee.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestResultsResponse {
    private Long testId;
    private Long userId;
    private String testType;
    private String result;
    private LocalDate datePerformed;
    private String labName;
}
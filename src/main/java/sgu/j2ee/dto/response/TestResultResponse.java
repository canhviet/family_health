package sgu.j2ee.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TestResultResponse {
    private Long userId;
    private String testType;
    private String result;
    private LocalDate datePerformed;
    private String labName;
    private Long testId;
}

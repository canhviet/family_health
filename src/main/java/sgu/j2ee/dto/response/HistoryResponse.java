package sgu.j2ee.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HistoryResponse {
    Long historyId;
    String condition;
    LocalDate diagnosisDate;
    String treatingDoctor;
    String notes;
    LocalDate revisitDate; 
    String doctorName;
    Long userId;
}

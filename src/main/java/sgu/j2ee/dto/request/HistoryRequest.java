package sgu.j2ee.dto.request;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class HistoryRequest {
    String condition;
    LocalDate diagnosisDate;
    String treatingDoctor;
    String notes;
    LocalDate revisitDate; 
    Long doctorUserId;
    Long userId;
}

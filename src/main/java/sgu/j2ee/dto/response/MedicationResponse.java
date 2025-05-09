package sgu.j2ee.dto.response;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MedicationResponse {
    String dosage;
    String frequency;
    String instructions;
    String medicationName;
    String quantity;
    Date startDate;
    Date endDate;
    Long medicationId;
}

package sgu.j2ee.dto.request;

import java.util.Date;
import java.util.List;

import lombok.Getter;

@Getter
public class PrescriptionRequest {
    String notes;
    Long doctorUserId;
    Long userId;
    List<MedicationRequest> medications;
    Date presciptionDate;
}

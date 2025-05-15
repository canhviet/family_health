package sgu.j2ee.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PresciptionResponse {
    private Long userId;
    private String doctorName;
    private Date prescriptionDate;
    private String notes;
    private List<MedicationResponse> medications;
    private Long prescriptionId;
}

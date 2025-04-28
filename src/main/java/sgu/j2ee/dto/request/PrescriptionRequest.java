package sgu.j2ee.dto.request;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


import lombok.Data;

@Data 
public class PrescriptionRequest implements Serializable{
    private Long userId;
    private Long doctorUserId;
    private Date prescriptionDate;
    private String notes;
    private List<MedicationRequest> medications;
}


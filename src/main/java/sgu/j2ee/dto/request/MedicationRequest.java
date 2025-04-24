package sgu.j2ee.dto.request;

import java.util.Date;

import lombok.Getter;

@Getter
public class MedicationRequest {
    String dosage;
    String frequency;
    String instructions;
    String medicationName;
    String quantity;
    Date starDate;
    Date endDate;
}

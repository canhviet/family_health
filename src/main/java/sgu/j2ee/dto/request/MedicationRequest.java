package sgu.j2ee.dto.request;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MedicationRequest implements Serializable{
    String dosage;
    String frequency;
    String instructions;
    String medicationName;
    String quantity;
    Date startDate;
    Date endDate;
}

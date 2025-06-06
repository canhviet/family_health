package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "tbl_medications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicationId;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescriptions prescription; 

    private String medicationName;
    private String dosage;
    private String frequency;
    private String quantity;
    private String instructions;
    private Date startDate;
    private Date endDate;
}
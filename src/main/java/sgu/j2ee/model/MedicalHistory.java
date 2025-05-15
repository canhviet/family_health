package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Table(name = "tbl_medical_history")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long historyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_user_id", nullable = false)
    private User doctor;

    private String condition;
    private LocalDate diagnosisDate;
    private String treatingDoctor;
    private String notes;
    private LocalDate revisitDate; 
}

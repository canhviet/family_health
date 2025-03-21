package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "prescriptions")
@Data
public class Prescriptions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prescriptionId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "doctor_user_id")
    private User doctor; 

    private LocalDate prescriptionDate;
    private String notes;

    @OneToMany(mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<Medications> medications; 
}
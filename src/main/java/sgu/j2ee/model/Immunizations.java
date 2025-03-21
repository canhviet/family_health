package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_immunizations")
@Data
public class Immunizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long immunizationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String vaccineName;
    private LocalDate dateAdministered;
    private String provider;
}

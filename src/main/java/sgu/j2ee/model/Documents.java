package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_documents")
@Data
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long documentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistory medicalHistory;

    private String documentUrl;
    private String documentName;
    private LocalDateTime uploadDate;
}

package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_allergies")
@Data
public class Allergies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long allergyId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String allergen;
    private String reaction;
    private String severity;
}

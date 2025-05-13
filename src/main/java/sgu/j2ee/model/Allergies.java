package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_allergies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

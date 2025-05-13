package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "tbl_immunizations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Immunizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long immunizationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String vaccineName;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dateAdministered;

    private String provider;
}

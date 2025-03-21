package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_test_results")
@Data
public class TestResults {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long testId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String testType;
    private String result;
    private LocalDate datePerformed;
    private String labName;
}

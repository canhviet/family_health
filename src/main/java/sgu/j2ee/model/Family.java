package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_family")
@Data
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @OneToOne
    @JoinColumn(name = "head_of_household_id")
    private User headOfHousehold;
}

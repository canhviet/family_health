package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String passwordHash;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private String firstName;
    private String lastName;
    private String dob; 
    private String gender;
    private String contactInfo;
    private String specialty; 

    private String relationshipToHead;
}

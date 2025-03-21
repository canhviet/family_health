package sgu.j2ee.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name = "tbl_permission")
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long permissionId;

    private String permissionName;
    private String description;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}

package sgu.j2ee.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_user")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "family_id")
    private Family family;

    private String firstName;
    private String lastName;
    private LocalDate dob; 
    private String gender;
    private String phone;
    private String address;
    private String healthInsuranceCode;
    private String citizenId;
    private String relationshipToHead;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getPermissions().stream()
                .map(permission -> (GrantedAuthority) permission::getPermissionName)
                .collect(Collectors.toList());
    }

}

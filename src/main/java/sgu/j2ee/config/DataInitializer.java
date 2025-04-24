package sgu.j2ee.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import sgu.j2ee.model.Permission;
import sgu.j2ee.model.Role;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.PermissionRepository;
import sgu.j2ee.repository.RoleRepository;
import sgu.j2ee.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner{

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0 && permissionRepository.count() == 0 && roleRepository.count() == 0) {
            Set<Permission> permissions = Set.of(
                Permission.builder().permissionName("FULL_ACCESS").build(), 
                Permission.builder().permissionName("VIEW").build(),
                Permission.builder().permissionName("ADD").build(),
                Permission.builder().permissionName("UPDATE").build(),
                Permission.builder().permissionName("DELETE").build(),
                Permission.builder().permissionName("UPLOAD").build()
            );

            permissionRepository.saveAll(permissions);

            Set<Permission> fullPermission = new HashSet<>();
            fullPermission.add(permissionRepository.findByPermissionName("FULL_ACCESS"));

            Role roleAdmin = Role.builder().roleName("ADMIN").permissions(fullPermission).build();

            Role roleDoctor = Role.builder().roleName("DOCTOR").permissions(fullPermission).build();

            Set<Permission> patientPermission = new HashSet<>();
            patientPermission.add(permissionRepository.findByPermissionName("VIEW"));

            Role rolePatient = Role.builder().roleName("PATIENT").permissions(patientPermission).build();

            roleRepository.save(roleAdmin);
            roleRepository.save(roleDoctor);
            roleRepository.save(rolePatient);

            User adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123")) 
                .role(roleAdmin)
                .build();

            userRepository.save(adminUser);

            User doctorUser = User.builder()
                .username("DrThanh")
                .password(passwordEncoder.encode("drthanh"))
                .role(roleDoctor)
                .build();

            userRepository.save(doctorUser);

            User patientUser = User.builder()
                .username("patient1")
                .password(passwordEncoder.encode("patient1"))
                .role(rolePatient)
                .build();

            userRepository.save(patientUser);

        }
    }

}

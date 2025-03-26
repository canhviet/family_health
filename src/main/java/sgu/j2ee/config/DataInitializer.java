package sgu.j2ee.config;

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
                Permission.builder().permissionName("CREATE_USER").build(), 
                Permission.builder().permissionName("UPDATE_USER").build(),
                Permission.builder().permissionName("DELETE_USER").build(),
                Permission.builder().permissionName("READ_USER").build()
            );

            permissionRepository.saveAll(permissions);

            Role role = Role.builder().roleName("ADMIN").permissions(permissions).build();

            roleRepository.save(role);

            User adminUser = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin123")) 
                .role(role)
                .build();

            userRepository.save(adminUser);

        }
    }

}

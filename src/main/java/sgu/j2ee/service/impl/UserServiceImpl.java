package sgu.j2ee.service.impl;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.request.UserUpdateRequest;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.exception.ResourceNotFoundException;
import sgu.j2ee.model.Role;
import sgu.j2ee.model.User;
import sgu.j2ee.repository.RoleRepository;
import sgu.j2ee.repository.UserRepository;
import sgu.j2ee.service.UserService;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Email not found"));
    }

    @Override
    public Long saveUser(User user) {
        userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Long saveUser(UserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("username is existed!");
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        
        Role rolePatient = roleRepository.findByRoleName("PATIENT")
                .orElseThrow(() -> new RuntimeException("role name PATIENT not found"));
        
        Role roleDoctor = roleRepository.findByRoleName("DOCTOR")
                .orElseThrow(() -> new RuntimeException("role name DOCTOR not found"));
        
        if ("patient".equals(request.getRole())) {
            user.setRole(rolePatient);
        }
        
        if ("doctor".equals(request.getRole())) {
            user.setRole(roleDoctor);
        }
                

        userRepository.save(user);
        log.info("User has added successfully, userId={}", user.getUserId());

        return user.getUserId();
    }

    @Override
    public void updateUser(Long userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));
        user.setAddress(request.getAddress());
        user.setCitizenId(request.getCitizenId());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setHealthInsuranceCode(request.getHealthInsuranceCode());
        user.setPhone(request.getPhone());

        userRepository.save(user);
    }

    @Override
    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new InvalidDataException("User not found with id: " + userId));

        return UserResponse.builder()
            .address(user.getAddress())
            .cityzenId(user.getCitizenId())
            .dob(user.getDob())
            .email(user.getEmail())
            .familyId(user.getFamily() != null ? user.getFamily().getFamilyId() : -1L)
            .firstName(user.getFirstName())
            .healthInsuranceCode(user.getHealthInsuranceCode())
            .lastName(user.getLastName())
            .phone(user.getPhone())
            .username(user.getUsername())
            .gender(user.getGender())
            .userId(userId)
            .build();
    }

    @Override
    public void deleteUser(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
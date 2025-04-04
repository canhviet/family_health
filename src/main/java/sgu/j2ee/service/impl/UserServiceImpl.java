package sgu.j2ee.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.response.PageResponse;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.dto.response.UserResponse;
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
    public ResponseData<?> all_users() {
        List<User> users = userRepository.findAll();
        List<UserResponse> list = users.stream().map(user -> UserResponse.builder()
        .id(user.getUserId())
        .username(user.getUsername())
        .first_name(user.getFirstName())
        .last_name(user.getLastName())
        .build()).toList();

        return new ResponseData<>(HttpStatus.OK.value(), "get all users", list);
    }

    @Override
    public PageResponse<?> getAllUser(int pageNo, int pageSize) {
        Page<User> page = userRepository.findAll(PageRequest.of(pageNo, pageSize));

        List<UserResponse> list = page.stream().map(user -> UserResponse.builder()
                .id(user.getUserId())
                .username(user.getUsername())
                .first_name(user.getFirstName())
                .last_name(user.getLastName())
                .build())
                .toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages())
                .items(list)
                .build();
    }

    @Override
    public User getByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public Long saveUser(UserRequest request) {
        User user = User.builder()
                .firstName(request.getFirst_name())
                .lastName(request.getLast_name())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        
        String defaultRoleName = "ROLE_USER"; 
        Role role = roleRepository.findByRoleName(defaultRoleName)
                .orElseThrow(() -> new RuntimeException("Vai trò " + defaultRoleName + " không tồn tại"));
        
        
        user.setRole(role);
        
        userRepository.save(user);
        log.info("User has added successfully, userId={}", user.getUserId());

        return user.getUserId();
    }

    @Override
    public void updateUser(Long userId, UserRequest request) {
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public UserResponse getUser(Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'getUser'");
    }

    @Override
    public void deleteUser(Long userId) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
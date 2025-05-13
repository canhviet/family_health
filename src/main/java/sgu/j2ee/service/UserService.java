package sgu.j2ee.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.request.UserUpdateRequest;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.model.User;

public interface UserService {
    User getByUsername(String username);
    Long saveUser(UserRequest request);
    void updateUser(Long userId, UserUpdateRequest request);
    UserResponse getUser(Long userId);
    void deleteUser(Long userId);
    UserDetailsService userDetailsService();
    User getUserByEmail(String email);
    Long saveUser(User user);
}

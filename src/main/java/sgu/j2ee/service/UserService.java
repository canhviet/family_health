package sgu.j2ee.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.response.PageResponse;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.dto.response.UserResponse;
import sgu.j2ee.model.User;

public interface UserService {
    ResponseData<?> all_users();
    PageResponse<?> getAllUser(int pageNo, int pageSize);
    User getByUsername(String username);
    Long saveUser(UserRequest request);
    void updateUser(Long userId, UserRequest request);
    UserResponse getUser(Long userId);
    void deleteUser(Long userId);
    UserDetailsService userDetailsService();
}

package sgu.j2ee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.UserUpdateRequest;
import sgu.j2ee.dto.response.ResponseData;
import sgu.j2ee.service.UserService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseData<?> getById(@PathVariable Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(), "get user by Id", this.userService.getUser(userId));
    }

    @PutMapping("/update/{userUd}")
    public ResponseData<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        this.userService.updateUser(userId, request);
        return new ResponseData<>(HttpStatus.OK.value(), "update user by Id");
    }
}

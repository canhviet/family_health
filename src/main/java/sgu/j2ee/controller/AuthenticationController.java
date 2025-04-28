package sgu.j2ee.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.ResetPasswordDTO;
import sgu.j2ee.dto.request.SignInRequest;
import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.response.TokenResponse;
import sgu.j2ee.service.AuthenticationService;

import static org.springframework.http.HttpStatus.OK;

import java.io.UnsupportedEncodingException;

@Slf4j
@Validated
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/access-token")
    public ResponseEntity<TokenResponse> accessToken(@RequestBody SignInRequest request) {
        return new ResponseEntity<>(authenticationService.accessToken(request), OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody String email) throws MessagingException, UnsupportedEncodingException {
        return new ResponseEntity<>(authenticationService.forgotPassword(email), OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody @Valid ResetPasswordDTO request) {
        return new ResponseEntity<>(authenticationService.changePassword(request), OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest res) {
        return new ResponseEntity<>(authenticationService.register(res), OK);
    }

}

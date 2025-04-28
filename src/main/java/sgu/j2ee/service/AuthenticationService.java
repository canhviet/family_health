package sgu.j2ee.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sgu.j2ee.dto.request.ResetPasswordDTO;
import sgu.j2ee.dto.request.SignInRequest;
import sgu.j2ee.dto.request.UserRequest;
import sgu.j2ee.dto.response.TokenResponse;
import sgu.j2ee.exception.InvalidDataException;
import sgu.j2ee.model.User;

import static sgu.j2ee.util.TokenType.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;
    private final MailService mailService;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse accessToken(SignInRequest signInRequest) {
        log.info("---------- authenticate ----------");

        var user = userService.getByUsername(signInRequest.getUsername());
        if (!user.isEnabled()) {
            throw new InvalidDataException("User not active");
        }

        List<String> roles = new ArrayList<>();
        List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword(), authorities)
        );

        // create new access token
        String accessToken = jwtService.generateToken(user);

        // create new refresh token
        String refreshToken = jwtService.generateRefreshToken(user);

        // save token to db

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getUserId())
                .build();
    }

    /**
     * Forgot password
     *
     * @param email
     */
    public String forgotPassword(String email) throws MessagingException, UnsupportedEncodingException {
        log.info("---------- forgotPassword ----------{}", email);

        // check email exists or not
        User user = userService.getUserByEmail(email);

        // generate reset token
        String resetToken = jwtService.generateResetToken(user);

        // save to db


        // TODO send email to user
        String confirmLink = String.format("http://localhost:4200/change-password?token=%s", resetToken);

        mailService.sendEmail(email, "forgot-password", "click the link below to change your password " + confirmLink, null);

        return resetToken;
    }

    public String changePassword(ResetPasswordDTO request) {
        log.info("---------- changePassword ----------");

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new InvalidDataException("Passwords do not match");
        }

        // get user by reset token
        var user = validateToken(request.getSecretKey());

        // update password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userService.saveUser(user);

        return "Changed";
    }

    public String register(UserRequest res) {
        userService.saveUser(res);
        return "register success";
    }

    private User validateToken(String token) {
        var userName = jwtService.extractUsername(token, RESET_TOKEN);

        var user = userService.getByUsername(userName);

        return user;
    }
}
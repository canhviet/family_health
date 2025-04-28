package sgu.j2ee.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import sgu.j2ee.util.TokenType;

public interface JwtService {
    String generateToken(UserDetails user);

    String generateRefreshToken(UserDetails user);

    String generateResetToken(UserDetails user);

    String extractUsername(String token, TokenType type);

    boolean isValid(String token, TokenType type, UserDetails user);

    List<String> extractPermissions(String token, TokenType tokenType);
    
}

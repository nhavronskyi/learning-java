package security.lesson_1.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {
    String createToken(UserDetails login);

    String getLoginFromToken(String token);

    boolean validateToken(String token);
}

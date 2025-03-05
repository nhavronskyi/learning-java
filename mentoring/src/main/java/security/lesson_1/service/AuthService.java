package security.lesson_1.service;

import security.lesson_1.request.AuthRequest;
import security.lesson_1.request.AuthResponse;

public interface AuthService {
    AuthResponse register(AuthRequest authRequest);

    AuthResponse login(AuthRequest authRequest);

    void logout(String token);
}

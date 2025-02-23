package security.lesson_1.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.lesson_1.dto.User;
import security.lesson_1.repository.UserRepository;
import security.lesson_1.request.AuthRequest;
import security.lesson_1.request.AuthResponse;
import security.lesson_1.service.AuthService;
import security.lesson_1.service.JwtService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse register(AuthRequest authRequest) {
        return Optional.of(userRepository.save(createUser(authRequest)))
                .map(u -> new AuthResponse(jwtService.createToken(u), HttpStatus.OK))
                .orElseGet(() -> new AuthResponse(null, HttpStatus.CONFLICT));
    }

    private User createUser(AuthRequest authRequest) {
        return new User(authRequest.login(), passwordEncoder.encode(authRequest.password()));
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.login(), authRequest.password()));
        log.info("User {} logged in", authRequest.login());
        return userRepository.findByUsername(authRequest.login())
                .map(jwtService::createToken)
                .map(token -> new AuthResponse(token, HttpStatus.OK))
                .orElseGet(() -> new AuthResponse(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public void logout(String token) {
        if (jwtService.getLoginFromToken(token) == null) {
            throw new UsernameNotFoundException("Invalid token");
        }
    }
}

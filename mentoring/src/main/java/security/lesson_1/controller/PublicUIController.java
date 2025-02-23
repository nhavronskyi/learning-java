package security.lesson_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import security.lesson_1.request.AuthRequest;
import security.lesson_1.request.AuthResponse;
import security.lesson_1.service.AuthService;

@RestController
@RequestMapping("public")
@RequiredArgsConstructor

public class PublicUIController {
    private final AuthService authService;

    @GetMapping("hello")
    public String hello() {
        return "Hello, World!";
    }

    @PostMapping("login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }

    @PostMapping("register")
    public AuthResponse register(@RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }
}

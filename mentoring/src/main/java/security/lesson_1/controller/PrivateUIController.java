package security.lesson_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import security.lesson_1.model.UserProfile;
import security.lesson_1.service.AuthService;
import security.lesson_1.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("private")
public class PrivateUIController {
    private final UserService userService;
    private final AuthService authService;

    @RequestMapping("hello")
    public String hello() {
        return "Hello, Private World!";
    }

    @RequestMapping("logout")
    public void logout(@RequestBody String token) {
        authService.logout(token);
    }

    @RequestMapping("profile")
    public UserProfile profile() {
        return userService.getCurrentUser();
    }
}

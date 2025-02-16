package databases.lesson_1.homework.controller;

import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(User user) {
        userService.createUser(user);
    }

    @PostMapping("deposit-money")
    public void depositMoney(@RequestBody User user, @RequestParam Double amount) {
        userService.depositMoney(user, amount);
    }

    @PostMapping("withdraw-money")
    public void withdrawMoney(User user, Double amount) {
        userService.withdrawMoney(user, amount);
    }

    @GetMapping
    public User getUser(Long id) {
        return userService.findUserById(id);
    }

    @GetMapping("all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping
    public void updateUser(User user) {
        userService.updateUser(user);
    }

    @DeleteMapping
    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}

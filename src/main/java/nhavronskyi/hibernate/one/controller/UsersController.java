package nhavronskyi.hibernate.one.controller;

import lombok.RequiredArgsConstructor;
import nhavronskyi.hibernate.one.model.User;
import nhavronskyi.hibernate.one.model.UserStatus;
import nhavronskyi.hibernate.one.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("hibernate/one")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @GetMapping("list")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("add")
    public Map<User, UserStatus> addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("delete")
    public Map<User, UserStatus> deleteUser(@RequestBody long id) {
        return userService.deleteUser(id);
    }

    @PutMapping("update")
    public Map<User, UserStatus> updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}

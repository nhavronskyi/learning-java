package databases.lesson_1.homework.controller;

import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.repository.UserRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerTest {
    @Autowired
    private UserController userController;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(0)
    void init() {
        var users = List.of(
                new User("John", "john.doe@gmail"),
                new User("John", "john.doe2@gmail"),
                new User("John", "john.doe3@gmail")
        );
        userRepository.saveAll(users);
    }

    @Test
    @Order(1)
    void getAllUsers() {
        var users = userController.getAllUsers();
        assertEquals(3, users.size());
    }

    @Test
    @Order(2)
    void getUserById() {
        var user = userController.getUser(1L);
        assertNotNull(user);
    }

    @Test
    @Order(3)
    void createUser() {
        var user = new User("Jane", "jane.doe@gmail");
        userController.createUser(user);
        var users = userController.getAllUsers();
        assertEquals(4, users.size());
    }

    @Test
    @Order(4)
    void updateUser() {
        var user = userController.getUser(1L);
        assertNotNull(user);
        user.setEmail("updated.email@gmail");
        userController.updateUser(user);
        var updatedUser = userController.getUser(1L);
        assertEquals("updated.email@gmail", updatedUser.getEmail());
    }

    @Test
    @Order(5)
    void deleteUser() {
        userController.deleteUser(1L);
        var users = userController.getAllUsers();
        assertEquals(3, users.size());
    }
}

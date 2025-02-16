package databases.lesson_1.homework.service;

import databases.lesson_1.homework.dto.User;

import java.util.List;

public interface UserService {
    void createUser(User user);

    void depositMoney(User user, Double amount);

    void withdrawMoney(User user, Double amount);

    User findUserById(Long id);

    List<User> findAllUsers();

    void updateUser(User user);

    void deleteUser(Long id);
}

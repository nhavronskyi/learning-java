package databases.lesson_1.homework.service.impl;

import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.repository.UserRepository;
import databases.lesson_1.homework.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public synchronized void createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with email " + user.getEmail() + " already exists");
        } else {
            userRepository.save(user);
            System.out.println(userRepository.findByEmail(user.getEmail()));
        }
    }

    public void depositMoney(User user, Double amount) {
        user.depositMoney(amount);
        userRepository.save(user);
    }

    @Override
    public void withdrawMoney(User user, Double amount) {
        user.withdrawMoney(amount);
        userRepository.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

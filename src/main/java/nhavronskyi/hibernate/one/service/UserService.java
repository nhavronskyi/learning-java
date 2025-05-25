package nhavronskyi.hibernate.one.service;

import lombok.RequiredArgsConstructor;
import nhavronskyi.hibernate.one.model.User;
import nhavronskyi.hibernate.one.model.UserStatus;
import nhavronskyi.hibernate.one.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> getUsers() {
        return userRepository.selectAllUsers();
    }

    public Map<User, UserStatus> addUser(User user) {
        return userRepository.addUser(user);
    }

    public Map<User, UserStatus> deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }

    public Map<User, UserStatus> updateUser(User user) {
        return userRepository.updateUser(user);
    }
}

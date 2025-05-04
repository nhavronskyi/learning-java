package nhavronskyi.hibernate.one.service;

import lombok.RequiredArgsConstructor;
import nhavronskyi.hibernate.one.model.User;
import nhavronskyi.hibernate.one.model.UserStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {


    public List<User> getUsers() {
        return List.of();
    }

    public Map<User, UserStatus> addUser(User user) {
        return Map.of();
    }

    public Map<User, UserStatus> deleteUser(Long id) {
        return Map.of();
    }

    public Map<User, UserStatus> updateUser(User user) {
        return Map.of();
    }
}

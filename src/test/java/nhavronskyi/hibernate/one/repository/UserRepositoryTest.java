package nhavronskyi.hibernate.one.repository;

import nhavronskyi.BaseLearningJavaTest;
import nhavronskyi.hibernate.one.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

class UserRepositoryTest extends BaseLearningJavaTest {
    @Autowired
    UserRepository userRepository;

    private static User getUser(int id, String test) {
        return new User(id, test);
    }

    @Test
    void shouldSelectAllUsers() {
        List<User> testUsers = List.of(
                getUser(1, "test"),
                getUser(2, "test2"),
                getUser(3, "test3")
        );
        Assertions.assertEquals(testUsers, userRepository.selectAllUsers());
    }
}

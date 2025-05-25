package nhavronskyi.hibernate.one.repository;

import nhavronskyi.BaseLearningJavaTest;
import nhavronskyi.hibernate.one.model.User;
import nhavronskyi.hibernate.one.model.UserStatus;
import nhavronskyi.hibernate.one.utils.MapUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryTest extends BaseLearningJavaTest {
    @Autowired
    UserRepository userRepository;

    private static User getUserStatusMap(int id, String test) {
        return new User(id, test);
    }

    private static User getTestUser4() {
        return new User(4, "test4");
    }

    @Test
    @Order(0)
    void shouldSelectAllUsers() {
        List<User> testUsers = List.of(
            getUserStatusMap(1, "test"),
            getUserStatusMap(2, "test2"),
            getUserStatusMap(3, "test3")
        );
        assertEquals(testUsers, userRepository.selectAllUsers());
    }

    @Test
    @Order(1)
    void shouldAddUser() {
        Map<User, UserStatus> statusMap = userRepository.addUser(getTestUser4());


        assertEquals(UserStatus.CREATED, MapUtils.getValue(statusMap.values()));

        List<User> users = userRepository.selectAllUsers();
        assertEquals(4, users.size());
        assertTrue(users.contains(getTestUser4()));
    }

    @Test
    @Order(2)
    void shouldDeleteUser() {
        Map<User, UserStatus> statusMap = userRepository.deleteUser(4L);

        assertEquals(UserStatus.DELETED, MapUtils.getValue(statusMap.values()));

        assertEquals(3, userRepository.selectAllUsers().size());
        assertFalse(userRepository.selectAllUsers().contains(getTestUser4()));
    }

    @Test
    @Order(3)
    void shouldReturnNullIfUserDoesNotExist() {
        Map<User, UserStatus> userUserStatusMap = userRepository.deleteUser(new User(999, "error").getId());

        assertEquals(UserStatus.NO_SUCH_USER, MapUtils.getValue(userUserStatusMap.values()));
        assertEquals(3, userRepository.selectAllUsers().size());
    }

    @Test
    @Order(4)
    void shouldUpdateUser() {
        User user = new User(1, "updated");

        userRepository.updateUser(user);

        List<User> users = userRepository.selectAllUsers();
        assertTrue(users.contains(user));
    }

    @Test
    @Order(4)
    void shouldNotAddUserWhileUpdating() {
        User user = getTestUser4();

        userRepository.updateUser(user);

        List<User> users = userRepository.selectAllUsers();
        assertFalse(users.contains(user));
        assertEquals(3, users.size());
    }
}

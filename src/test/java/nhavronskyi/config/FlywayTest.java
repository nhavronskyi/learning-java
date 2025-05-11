package nhavronskyi.config;

import lombok.SneakyThrows;
import nhavronskyi.BaseLearningJavaTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlywayTest extends BaseLearningJavaTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @SneakyThrows
    @Test
    void shouldLoadUsersFromFlywayMigration() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
        assertEquals(3, count);
    }
}

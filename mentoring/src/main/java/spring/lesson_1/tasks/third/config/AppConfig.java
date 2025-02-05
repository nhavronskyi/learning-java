package spring.lesson_1.tasks.third.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import spring.lesson_1.tasks.third.dto.Database;
import spring.lesson_1.tasks.third.dto.impl.MockDatabase;
import spring.lesson_1.tasks.third.dto.impl.MySQLDatabase;
import spring.lesson_1.tasks.third.repository.UserRepository;

public class AppConfig {

    @Bean
    public UserRepository userRepository(Database database) {
        return new UserRepository(database);
    }

    @Bean
    @Conditional(MySQLDatabase.class)
    public Database mySqlDatabase() {
        return new MySQLDatabase();
    }

    @Bean
    @Conditional(MockDatabase.class)
    public Database mockDatabase() {
        return new MockDatabase();
    }
}

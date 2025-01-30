package lesson_spring_1.homework.tasks.third.config;

import lesson_spring_1.homework.tasks.third.dto.Database;
import lesson_spring_1.homework.tasks.third.dto.impl.MockDatabase;
import lesson_spring_1.homework.tasks.third.dto.impl.MySQLDatabase;
import lesson_spring_1.homework.tasks.third.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

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

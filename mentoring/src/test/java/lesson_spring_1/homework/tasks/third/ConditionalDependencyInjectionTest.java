package lesson_spring_1.homework.tasks.third;

import lesson_spring_1.homework.tasks.third.config.AppConfig;
import lesson_spring_1.homework.tasks.third.dto.impl.MockDatabase;
import lesson_spring_1.homework.tasks.third.dto.impl.MySQLDatabase;
import lesson_spring_1.homework.tasks.third.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConditionalDependencyInjectionTest {

    @Test
    void mySqlDatabaseOptionTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.performDatabaseOperation();

        String className = userRepository.database().getClass().getCanonicalName();
        assertEquals(MySQLDatabase.class.getCanonicalName(), className);
        context.close();
    }

    @Test
    void mockDatabaseOptionTest() throws IOException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().getPropertySources().addLast(new ResourcePropertySource("classpath:lesson_spring_1/homework/tasks/third/application.properties"));
        context.register(AppConfig.class);
        context.refresh();

        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.performDatabaseOperation();

        String className = userRepository.database().getClass().getCanonicalName();
        assertEquals(MockDatabase.class.getCanonicalName(), className);
        context.close();
    }
}

package spring.lesson_1.homework.tasks.third;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.support.ResourcePropertySource;
import spring.lesson_1.tasks.third.config.AppConfig;
import spring.lesson_1.tasks.third.dto.impl.MockDatabase;
import spring.lesson_1.tasks.third.dto.impl.MySQLDatabase;
import spring.lesson_1.tasks.third.repository.UserRepository;

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
        context.getEnvironment().getPropertySources().addLast(new ResourcePropertySource("classpath:spring/lesson_1/homework/tasks/third/application.properties"));
        context.register(AppConfig.class);
        context.refresh();

        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.performDatabaseOperation();

        String className = userRepository.database().getClass().getCanonicalName();
        assertEquals(MockDatabase.class.getCanonicalName(), className);
        context.close();
    }
}

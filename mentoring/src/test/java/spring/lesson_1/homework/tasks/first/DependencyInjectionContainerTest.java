package spring.lesson_1.homework.tasks.first;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.lesson_1.tasks.first.config.Container;
import spring.lesson_1.tasks.first.dto.Database;
import spring.lesson_1.tasks.first.dto.impl.MySQLDatabase;
import spring.lesson_1.tasks.first.repository.UserRepository;

class DependencyInjectionContainerTest {

    @Test
    void shouldCreateNewInstanceIfIsAddedToBindings() {
        Container container = new Container();
        container.bind(Database.class, new MySQLDatabase());
        container.bind(UserRepository.class);

        UserRepository userRepo = container.make(UserRepository.class);

        Assertions.assertNotNull(userRepo);
        Assertions.assertInstanceOf(MySQLDatabase.class, userRepo.getDb());
    }

    @Test
    void shouldThrowExceptionIfNoBindingFound() {
        Container container = new Container();

        Assertions.assertThrows(RuntimeException.class,
                () -> container.make(Database.class));
    }
}

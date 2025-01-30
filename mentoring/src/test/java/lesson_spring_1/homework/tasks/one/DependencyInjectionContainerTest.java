package lesson_spring_1.homework.tasks.one;

import lesson_spring_1.homework.tasks.one.config.Container;
import lesson_spring_1.homework.tasks.one.dtos.Database;
import lesson_spring_1.homework.tasks.one.dtos.impl.MySQLDatabase;
import lesson_spring_1.homework.tasks.one.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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

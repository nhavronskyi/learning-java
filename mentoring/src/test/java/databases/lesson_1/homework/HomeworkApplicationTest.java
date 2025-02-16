package databases.lesson_1.homework;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = HomeworkApplication.class)
@ActiveProfiles("test")
class HomeworkApplicationTest {

    @Test
    void contextLoads() {
    }
}

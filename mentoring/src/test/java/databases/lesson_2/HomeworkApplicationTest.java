package databases.lesson_2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = HomeworkApplicationTest.class)
@ActiveProfiles("test")
class HomeworkApplicationTest {

    @Test
    void shouldBuld() {
    }
}

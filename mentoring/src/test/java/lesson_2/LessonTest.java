package lesson_2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class LessonTest {
    @Test
    void shouldThrowInterruptedException() throws InterruptedException {
        Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));

        t.start();
        t.join();

        assertThrows(IllegalThreadStateException.class, t::start);
    }
}

package lesson_2.homework.tasks;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadSafeCounterTest {

    @RepeatedTest(5)
    void threadSafeCounterTest() throws InterruptedException {
        ThreadSafeCounter.Counter counter = new ThreadSafeCounter.Counter();

        Runnable runnable = () -> {
            for (int i = 0; i < 2000; i++) {
                counter.increment();
            }
        };

        List<Thread> threads = IntStream.range(0, 10)
                .mapToObj(_ -> new Thread(runnable))
                .toList();

        threads.forEach(Thread::start);

        for (Thread thread : threads) {
            thread.join();
        }

        assertEquals(20_000, counter.getCount());
    }

}
package lesson_2.homework.tasks;

import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class SynchronizedCacheTest {

    @RepeatedTest(10)
    void synchronizedCacheTest() throws InterruptedException {
        int threadCount = 10;
        int operationsPerThread = 1000;

        SynchronizedCache<Integer, Integer> cache = new SynchronizedCache<>();
        ConcurrentHashMap<Integer, Integer> observedMap = new ConcurrentHashMap<>();


        try (ExecutorService executor = Executors.newFixedThreadPool(threadCount * 2)) {

            // Writer tasks
            for (int i = 0; i < threadCount; i++) {
                int threadId = i;
                executor.submit(() -> {
                    for (int j = 0; j < operationsPerThread; j++) {
                        cache.put(threadId * operationsPerThread + j, j);
                    }
                });
            }

            // Reader tasks
            for (int i = 0; i < threadCount; i++) {
                int threadId = i;
                executor.submit(() -> {
                    for (int j = 0; j < operationsPerThread; j++) {
                        int key = threadId * operationsPerThread + j;
                        observedMap.put(key, cache.get(key));
                    }
                });
            }

            if (!executor.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                assertFalse(executor.isTerminated(), "Executor did not terminate");
            }
        }

        IntStream.range(0, threadCount * operationsPerThread)
                .forEach(i -> assertEquals(i % operationsPerThread, cache.get(i),
                        "Cache value mismatch for key: " + i));

        assertEquals(cache.size(), observedMap.size(), "Observed Map mismatch");
    }

}

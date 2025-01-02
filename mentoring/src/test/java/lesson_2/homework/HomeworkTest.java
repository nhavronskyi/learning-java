package lesson_2.homework;

import lesson_2.homework.tasks.ReadWriteLockExample;
import lesson_2.homework.tasks.SynchronizedCache;
import lesson_2.homework.tasks.ThreadSafeCounter;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HomeworkTest {

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

    @RepeatedTest(5)
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

            if (!executor.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                assertFalse(executor.isTerminated(), "Executor did not terminate");
            }
        }

        IntStream.range(0, threadCount * operationsPerThread)
                .forEach(i -> assertEquals(i % operationsPerThread, cache.get(i),
                        "Cache value mismatch for key: " + i));

        assertEquals(cache.size(), observedMap.size(), "Observed Map mismatch");
    }

    @RepeatedTest(5)
    void readWriteLockExample() throws InterruptedException {
        ReadWriteLockExample<Integer> rwLock = new ReadWriteLockExample<>();

        int numReaders = 5;
        int numWriters = 3;

        var readers = IntStream.range(0, numReaders)
                .mapToObj(_ -> new Thread(() -> {
                    try {
                        rwLock.readData().wait(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }))
                .toList();

        var writers = IntStream.rangeClosed(1, numWriters)
                .mapToObj(i -> new Thread(() -> rwLock.writeData(i * 10)))
                .toList();

        readers.forEach(Thread::start);
        writers.forEach(Thread::start);

        for (Thread reader : readers) {
            reader.join();
        }
        for (Thread writer : writers) {
            writer.join();
        }
    }
}

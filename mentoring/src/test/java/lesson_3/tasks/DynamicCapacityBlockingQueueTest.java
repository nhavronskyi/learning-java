package lesson_3.tasks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DynamicCapacityBlockingQueueTest {

    private DynamicCapacityBlockingQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new DynamicCapacityBlockingQueue<>(2);
    }

    @Test
    void testBasicPutAndTake() throws InterruptedException {
        queue.put(1);
        queue.put(2);
        assertEquals(1, queue.take());
        assertEquals(2, queue.take());
    }

    @Test
    void testBlockingPutWhenFull() throws InterruptedException {
        queue.put(1);
        queue.put(2);

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    queue.take();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            queue.put(3);

            assertEquals(2, queue.take());
            assertEquals(3, queue.take());
        }
    }

    @Test
    void testBlockingTakeWhenEmpty() throws InterruptedException {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(() -> {
                try {
                    Thread.sleep(1000);
                    queue.put(1);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            long startTime = System.currentTimeMillis();
            assertEquals(1, queue.take());
            long elapsedTime = System.currentTimeMillis() - startTime;

            assertTrue(elapsedTime >= 1000);
        }
    }

    @Test
    void testDynamicCapacityIncrease() throws InterruptedException {
        queue.put(1);
        queue.put(2);

        queue.setMaxCapacity(4);

        queue.put(3);
        queue.put(4);

        assertEquals(1, queue.take());
        assertEquals(2, queue.take());
        assertEquals(3, queue.take());
        assertEquals(4, queue.take());
    }

    @Test
    void testDynamicCapacityDecrease() throws InterruptedException {
        queue.put(1);
        queue.put(2);

        queue.setMaxCapacity(1);

        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            executorService.execute(getRunnable(1000));
            executorService.execute(getRunnable(3000));

            long startTime = System.currentTimeMillis();
            queue.put(3);
            long elapsedTime = System.currentTimeMillis() - startTime;
            assertTrue(elapsedTime >= 3000, "Expected blocking put to take at least 3000 ms");
        }

        assertEquals(3, queue.take());
    }

    private Runnable getRunnable(int time) {
        return () -> {
            try {
                Thread.sleep(time);
                queue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
    }

    @Test
    void testThreadSafetyWithMultipleProducersAndConsumers() throws InterruptedException {
        int producerCount = 5;
        int consumerCount = 5;

        try (ExecutorService producerExecutor = Executors.newFixedThreadPool(producerCount);
             ExecutorService consumerExecutor = Executors.newFixedThreadPool(consumerCount)) {

            for (int i = 0; i < producerCount; i++) {
                final int value = i + 1;
                producerExecutor.submit(() -> {
                    try {
                        queue.put(value);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            for (int i = 0; i < consumerCount; i++) {
                consumerExecutor.submit(() -> {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }

            producerExecutor.shutdown();
            consumerExecutor.shutdown();

            assertTrue(producerExecutor.awaitTermination(5, TimeUnit.SECONDS));
            assertTrue(consumerExecutor.awaitTermination(5, TimeUnit.SECONDS));
        }
    }
}

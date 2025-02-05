package concurrency.lesson_1.homework.tasks;

import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ProtectSharedResourceWithBlockingQueueTest {

    @RepeatedTest(2)
    void testProducerConsumer() throws InterruptedException {
        ProtectSharedResourceWithBlockingQueue.Producer producer = new ProtectSharedResourceWithBlockingQueue.Producer();
        ProtectSharedResourceWithBlockingQueue.Consumer consumer = new ProtectSharedResourceWithBlockingQueue.Consumer();

        try (ExecutorService executorService = Executors.newFixedThreadPool(10)) {
            executorService.execute(producer);
            executorService.execute(consumer);

            executorService.shutdown();

            boolean finished = executorService.awaitTermination(5, TimeUnit.SECONDS);
            assertTrue(finished, "Producer and Consumer did not finish in time");

//            assertTrue(ProtectSharedResourceWithBlockingQueue.queue.isEmpty(), "Queue is not empty");
        }
    }
}

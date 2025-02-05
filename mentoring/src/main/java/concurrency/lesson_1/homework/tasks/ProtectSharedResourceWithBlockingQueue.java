package concurrency.lesson_1.homework.tasks;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Protect a Shared Resource with BlockingQueue
 * <p>
 * Implement a producer-consumer system using a thread-safe queue to protect shared resources.
 */
@Slf4j
public class ProtectSharedResourceWithBlockingQueue {
    public static final Queue<Integer> queue = new LinkedList<>();
    private static final int Max_CAPACITY = 10; // Maximum capacity of the queue

    public static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    produce(i);
                    Thread.sleep(50); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private synchronized void produce(int item) throws InterruptedException {
            int waitingCounter = 5;
            while (queue.size() == Max_CAPACITY && waitingCounter > 0) {
                log.debug("Queue is full. Producer is waiting.");
                wait(100);

                waitingCounter--;
            }

            queue.add(item);
            log.debug("Produced: {}", item);

            notify();
        }
    }

    // Consumer Thread
    public static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                int retryingCounter = 20;
                while (retryingCounter > 0) {
                    consume();
                    Thread.sleep(50); // Simulate some work
                    retryingCounter--;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private synchronized void consume() throws InterruptedException {
            int waitingCounter = 5;
            while (queue.isEmpty() && waitingCounter > 0) {
                log.debug("Queue is empty. Consumer is waiting.");
                wait(50);

                waitingCounter--;
            }

            Integer item = queue.poll();
            log.debug("Consumed: {}", item);

            notify();
        }
    }
}

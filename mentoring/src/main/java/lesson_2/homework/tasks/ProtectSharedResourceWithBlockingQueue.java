package lesson_2.homework.tasks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Protect a Shared Resource with BlockingQueue
 * <p>
 * Implement a producer-consumer system using a thread-safe queue to protect shared resources.
 */
public class ProtectSharedResourceWithBlockingQueue {
    public static final Queue<Integer> queue = new LinkedList<>();
    private static final int Max_CAPACITY = 10; // Maximum capacity of the queue

    static class Producer implements Runnable {
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
                System.out.println("Queue is full. Producer is waiting.");
                wait(100);

                waitingCounter--;
            }

            queue.add(item);
            System.out.println("Produced: " + item);

            notify();
        }
    }

    // Consumer Thread
    static class Consumer implements Runnable {
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
                System.out.println("Queue is empty. Consumer is waiting.");
                wait(50);

                waitingCounter--;
            }

            Integer item = queue.poll();
            System.out.println("Consumed: " + item);

            notify();
        }
    }
}

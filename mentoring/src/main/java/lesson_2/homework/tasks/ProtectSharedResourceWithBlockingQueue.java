package lesson_2.homework.tasks;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Protect a Shared Resource with BlockingQueue
 * <p>
 * Implement a producer-consumer system using a thread-safe queue to protect shared resources.
 */
public class ProtectSharedResourceWithBlockingQueue {
    private static final int CAPACITY = 10; // Maximum capacity of the queue
    private static final Queue<Integer> queue = new LinkedList<>();

    // Producer Thread
    static class Producer implements Runnable {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 20; i++) {
                    produce(i);
                    Thread.sleep(500); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private synchronized void produce(int item) throws InterruptedException {
            while (queue.size() == CAPACITY) {
                // If the queue is full, wait until there is space
                System.out.println("Queue is full. Producer is waiting.");
                wait();
            }

            // Add the item to the queue
            queue.add(item);
            System.out.println("Produced: " + item);

            // Notify the consumer that it can consume an item
            notify();
        }
    }

    // Consumer Thread
    static class Consumer implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    consume();
                    Thread.sleep(1000); // Simulate some work
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private synchronized void consume() throws InterruptedException {
            while (queue.isEmpty()) {
                // If the queue is empty, wait until there is an item
                System.out.println("Queue is empty. Consumer is waiting.");
                wait();
            }

            // Remove the item from the queue
            Integer item = queue.poll();
            System.out.println("Consumed: " + item);

            // Notify the producer that it can add an item to the queue
            notify();
        }
    }
}

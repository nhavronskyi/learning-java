package concurrency.lesson_1.homework.tasks;

import lombok.Getter;

public class ThreadSafeCounter {

    /**
     * Implement a Thread-Safe Counter
     * <p>
     * Write a thread-safe class Counter that allows multiple threads to increment a counter without causing race conditions.
     */
    @Getter
    public static class Counter {
        private int count = 0;

        public synchronized void increment() {
            count++;
        }
    }
}

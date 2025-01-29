package lesson_3.tasks;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class DynamicCapacityBlockingQueue<T> {
    private final ReentrantLock lock;
    private final Condition notFull;
    private final Condition notEmpty;
    private final BlockingQueue<T> queue;
    private volatile int maxCapacity;

    public DynamicCapacityBlockingQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        this.queue = new LinkedBlockingQueue<>();
        this.lock = new ReentrantLock();
        this.notFull = lock.newCondition();
        this.notEmpty = lock.newCondition();
        this.maxCapacity = initialCapacity;
    }

    public void setMaxCapacity(int newMaxCapacity) {
        if (newMaxCapacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than zero.");
        }
        lock.lock();
        try {
            maxCapacity = newMaxCapacity;
            notFull.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (queue.size() >= maxCapacity) {
                notFull.await();
            }
            queue.add(item);
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new QueueFullException();
        } finally {
            lock.unlock();
        }
    }

    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                notEmpty.await();
            }
            T item = queue.take();
            notFull.signal();
            return item;
        } catch (InterruptedException e) {
            throw new QueueEmptyException();
        } finally {
            lock.unlock();
        }
    }

    public static class QueueFullException extends RuntimeException {
        public QueueFullException() {
            super("Thread was interrupted while waiting to add an item.");
        }
    }

    public static class QueueEmptyException extends RuntimeException {
        public QueueEmptyException() {
            super("Thread was interrupted while waiting to take an item.");
        }
    }
}

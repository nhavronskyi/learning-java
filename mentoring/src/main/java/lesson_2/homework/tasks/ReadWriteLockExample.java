package lesson_2.homework.tasks;

import lombok.SneakyThrows;

/**
 * Create a Read-Write Lock Example
 * <p>
 * Implement a thread-safe data store that allows multiple threads to read data concurrently but only one thread to write.
 */
public class ReadWriteLockExample<T> {
    private T data;
    private int readers = 0;
    private boolean isWriting = false;

    @SneakyThrows
    public synchronized void readData() {
        while (isWriting || data == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        readers++;

        readers--;
        if (readers == 0) {
            notifyAll();
        }

    }

    @SneakyThrows
    public synchronized void writeData(T newData) {
        while (isWriting || readers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        isWriting = true;
        data = newData;

        Thread.sleep(100);

        isWriting = false;
        notifyAll();
    }
}

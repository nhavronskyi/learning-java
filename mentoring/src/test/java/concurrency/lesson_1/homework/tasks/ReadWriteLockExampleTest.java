package concurrency.lesson_1.homework.tasks;

import org.junit.jupiter.api.RepeatedTest;

import java.util.stream.IntStream;

class ReadWriteLockExampleTest {

    @RepeatedTest(5)
    void readWriteLockExample() throws InterruptedException {
        ReadWriteLockExample<Integer> rwLock = new ReadWriteLockExample<>();

        int numReaders = 5;
        int numWriters = 3;

        var readers = IntStream.range(0, numReaders)
                .mapToObj(_ -> new Thread(rwLock::readData))
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

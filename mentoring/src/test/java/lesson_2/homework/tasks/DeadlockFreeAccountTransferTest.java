package lesson_2.homework.tasks;

import org.junit.jupiter.api.RepeatedTest;

import java.util.List;
import java.util.stream.IntStream;

class DeadlockFreeAccountTransferTest {
    @RepeatedTest(5)
    void deadlockFreeAccountTransferTest() throws InterruptedException {

        DeadlockFreeAccountTransfer.Account account1 = new DeadlockFreeAccountTransfer.Account(1000);
        DeadlockFreeAccountTransfer.Account account2 = new DeadlockFreeAccountTransfer.Account(500);
        DeadlockFreeAccountTransfer dfat = new DeadlockFreeAccountTransfer(
                List.of(
                        account1,
                        account2
                ));

        int users = 2;

        var toOperationThreads = IntStream.rangeClosed(1, users)
                .mapToObj(_ -> new Thread(() -> dfat.transfer(account1, account2, 300)))
                .toList();

        var fromOperationThreads = IntStream.rangeClosed(1, users)
                .mapToObj(_ -> new Thread(() -> dfat.transfer(account2, account1, 500)))
                .toList();

        toOperationThreads.forEach(Thread::start);
        fromOperationThreads.forEach(Thread::start);

        for (Thread toOperationThread : toOperationThreads) {
            toOperationThread.join();
        }
        for (Thread fromOperationThread : fromOperationThreads) {
            fromOperationThread.join();
        }


        dfat.accounts().forEach(System.out::println);
    }

}
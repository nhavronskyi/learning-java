package concurrency.lesson_1.homework.tasks;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * Write a Deadlock-Free Account Transfer
 * <p>
 * Simulate a money transfer system where multiple threads can transfer funds between accounts. Ensure no deadlocks occur.
 */
public record DeadlockFreeAccountTransfer(List<Account> accounts) {

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public synchronized void transfer(Account from, Account to, Integer amount) {
        int fromId = accounts.indexOf(from);
        int toId = accounts.indexOf(to);

        Account firstLock = fromId < toId ? accounts.get(fromId) : accounts.get(toId);
        Account secondLock = fromId < toId ? accounts.get(toId) : accounts.get(fromId);

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (fromId == toId) {
                    throw new IllegalArgumentException("Cannot transfer to the same account.");
                }
                if (accounts.get(fromId).getBalance() < amount) {
                    throw new RuntimeException("Insufficient balance in account " + fromId);
                }
                accounts.get(fromId).withdraw(amount);
                accounts.get(toId).deposit(amount);
            }
        }
    }

    @Getter
    @ToString
    public static class Account {
        private int balance;

        public Account(int balance) {
            this.balance = balance;
        }

        public void deposit(int amount) {
            balance += amount;
        }

        public void withdraw(int amount) {
            balance -= amount;
        }

    }

}

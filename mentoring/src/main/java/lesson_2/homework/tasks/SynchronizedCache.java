package lesson_2.homework.tasks;

import lombok.SneakyThrows;

import java.util.HashMap;

/**
 * Build a Synchronized Cache
 * <p>
 * Create a thread-safe in-memory cache using HashMap that supports put() and get() operations.
 */
public class SynchronizedCache<K, V> {
    private final HashMap<K, V> map = new HashMap<>();

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }

    @SneakyThrows
    public synchronized V get(K key) {
        while (!map.containsKey(key)) {
            wait(10);
        }
        return map.get(key);
    }

    public int size() {
        return map.size();
    }

}

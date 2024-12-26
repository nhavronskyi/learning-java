package lesson_1.homework.tasks;

import java.util.function.Function;
import java.util.function.Predicate;

public interface GenericRetryUtility {
    // Generic Retry Utility: Create a generic retry utility that retries a failed operation. The utility should accept a lambda function and retry it based on a retry policy.
    static <T, R> R apply(
            Function<T, R> operation,
            Predicate<R> retryCondition,
            T input,
            int maxRetries) {

        for (int i = 0; i <= maxRetries; i++) {
            R result = operation.apply(input);
            if (retryCondition.test(result)) {
                return result;
            }
        }
        throw new RuntimeException("Max retry attempts reached without success");
    }
}

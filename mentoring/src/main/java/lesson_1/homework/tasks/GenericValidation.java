package lesson_1.homework.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public interface GenericValidation {
    // Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.
    static <T> void validate(T obj, List<Predicate<T>> predicators) {
        var indexes = IntStream.range(0, predicators.size())
                .filter(i -> !predicators.get(i).test(obj))
                .toArray();

        if (indexes.length != 0) {
            System.out.println("failed validation because of: " + Arrays.toString(indexes));
        }
    }
}

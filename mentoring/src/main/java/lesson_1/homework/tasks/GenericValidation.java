package lesson_1.homework.tasks;

import java.util.List;
import java.util.function.Predicate;

public interface GenericValidation {
    // Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.
    static <T> boolean validate(T obj, List<Predicate<T>> predicate) {
        return predicate.stream()
                .allMatch(p -> p.test(obj));
    }
}

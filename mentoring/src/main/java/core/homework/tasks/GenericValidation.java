package core.homework.tasks;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public interface GenericValidation {
    /**
     * Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.
     *
     * @return failed indexes
     */
    static <T> List<Integer> validate(T obj, List<Predicate<T>> predictors) {
        return IntStream.range(0, predictors.size())
                .filter(i -> !predictors.get(i).test(obj))
                .boxed()
                .toList();
    }
}

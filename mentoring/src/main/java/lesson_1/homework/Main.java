package lesson_1.homework;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        topNFinder(List.of(1, 2, 3), 2).forEach(System.out::println);
    }

    /**
     * Top-N Finder: Find the top N elements in a collection dynamically.
     *
     * @return {@link Collection} of any top n elements
     */
    public static Collection<?> topNFinder(Collection<?> collection, int n) {
        return collection.stream()
                .limit(n)
                .toList();
    }


    // TODO Dynamic Sorting Utility: Create a utility that dynamically sorts a list of objects based on runtime-provided field names and directions.

    // TODO Dynamic Query Builder: Implement a dynamic SQL query builder using Generics. The builder should accept criteria and return a query string while ensuring type safety for criteria values.

    // TODO Generic Retry Utility: Create a generic retry utility that retries a failed operation. The utility should accept a lambda function and retry it based on a retry policy.

    // TODO Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.

    // TODO Frequency Sort: Write a method that sorts a list of strings by their frequency (most frequent first).
}

package lesson_1.homework;

import lesson_1.homework.tasks.TomNFinder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Top-N Finder
        TomNFinder.getTopN()
                .apply(List.of(3, 2, 1), 2)
                .forEach(System.out::println);
    }

    // TODO Dynamic Sorting Utility: Create a utility that dynamically sorts a list of objects based on runtime-provided field names and directions.

    // TODO Dynamic Query Builder: Implement a dynamic SQL query builder using Generics. The builder should accept criteria and return a query string while ensuring type safety for criteria values.

    // TODO Generic Retry Utility: Create a generic retry utility that retries a failed operation. The utility should accept a lambda function and retry it based on a retry policy.

    // TODO Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.

    // TODO Frequency Sort: Write a method that sorts a list of strings by their frequency (most frequent first).
}

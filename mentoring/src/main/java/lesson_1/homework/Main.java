package lesson_1.homework;

import lesson_1.homework.tasks.DynamicSortingUtility;
import lesson_1.homework.tasks.TomNFinder;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static lesson_1.homework.helpers.PersonGenerator.Person;
import static lesson_1.homework.helpers.PersonGenerator.generatePerson;

public class Main {
    public static void main(String[] args) {
        topNFinder();

        dynamicSortingUtility();
    }

    private static void dynamicSortingUtility() {
        DynamicSortingUtility.sort(
                        List.of(1, generatePerson(2, "ann"), "temp", 3, 2, "a", generatePerson(1, "joe")),
                        Map.of(Person.class, Comparator.comparing(Person::name))
                )
                .entrySet()
                .forEach(System.out::println);
    }

    // Top-N Finder
    private static void topNFinder() {
        TomNFinder.getTopN()
                .apply(List.of(3, 2, 1), 2)
                .forEach(System.out::println);
    }

    // TODO Dynamic Query Builder: Implement a dynamic SQL query builder using Generics. The builder should accept criteria and return a query string while ensuring type safety for criteria values.

    // TODO Generic Retry Utility: Create a generic retry utility that retries a failed operation. The utility should accept a lambda function and retry it based on a retry policy.

    // TODO Generic Validation with Dynamic Rules: Create a utility to validate objects dynamically using rules provided at runtime. Each rule should be a Predicate<T> and applied sequentially.

    // TODO Frequency Sort: Write a method that sorts a list of strings by their frequency (most frequent first).
}

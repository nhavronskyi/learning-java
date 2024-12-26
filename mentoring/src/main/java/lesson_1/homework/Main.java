package lesson_1.homework;

import lesson_1.homework.tasks.*;

import java.util.*;

import static lesson_1.homework.helpers.PersonGenerator.Person;
import static lesson_1.homework.helpers.PersonGenerator.generatePerson;

public class Main {
    public static void main(String[] args) {
        topNFinder();
        dynamicSortingUtility();
        frequencySort();
        genericRetryUtility();
        genericValidation();

        // TODO Dynamic Query Builder: Implement a dynamic SQL query builder using Generics. The builder should accept criteria and return a query string while ensuring type safety for criteria values.
    }

    private static void genericValidation() {
        boolean validateResult = GenericValidation.validate("Hello", List.of(
                Objects::nonNull,
                o -> o.contains("e"),
                o -> o.equalsIgnoreCase("hello")
        ));
        System.out.println(validateResult);
    }

    private static void genericRetryUtility() {
        Integer apply = GenericRetryUtility.apply(
                x -> x - 1,
                r -> r <= 10,
                11,
                1
        );
        System.out.println(apply);
    }

    private static void frequencySort() {
        var list = new ArrayList<>(List.of("hello", "world", "as", "as", "as", "hello", "words"));
        FrequencySort.sort(list);

        System.out.println(list);
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
}

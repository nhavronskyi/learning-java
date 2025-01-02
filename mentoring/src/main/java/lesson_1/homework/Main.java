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
        dynamicQueryBuilder();
    }

    private static void dynamicQueryBuilder() {
        List<Integer> list = List.of(1, 2, 3, 4);

        var dqb = DynamicQueryBuilder.Builder
                .from(list)
                .select(x -> x)
                .where(x -> x % 2 == 0)
                .build();

        dqb.getList()
                .forEach(System.out::println);
    }

    private static void genericValidation() {
        GenericValidation.validate("Hello", List.of(
                Objects::nonNull,
                o -> o.contains("e"),
                o -> o.equalsIgnoreCase("hello"),
                o -> o.contains("1")
        ));
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

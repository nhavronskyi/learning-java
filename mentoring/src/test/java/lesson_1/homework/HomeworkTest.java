package lesson_1.homework;

import lesson_1.homework.helpers.PersonGenerator;
import lesson_1.homework.tasks.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static lesson_1.homework.helpers.PersonGenerator.generatePerson;
import static org.junit.jupiter.api.Assertions.*;

public class HomeworkTest {
    @Test
    void dynamicQueryBuilder() {
        List<Integer> list = List.of(1, 2, 3, 4);

        var dqb = DynamicQueryBuilder.Builder
                .from(list)
                .select(x -> x)
                .where(x -> x % 2 == 0)
                .build();

        assertEquals(List.of(2, 4), dqb.getList());
    }

    @Test
    void genericValidationShouldReturnEmptyList() {
        var results = GenericValidation.validate("Hello", List.of(
                Objects::nonNull,
                o -> o.contains("e"),
                o -> o.equalsIgnoreCase("hello")
        ));

        assertTrue(results.isEmpty());
    }

    @Test
    void genericValidationShouldReturnListWithIndexesFailedPredicates() {
        var results = GenericValidation.validate("Hello", List.of(
                Objects::nonNull,
                o -> o.contains("e"),
                o -> o.equalsIgnoreCase("hello"),
                o -> o.contains("1")
        ));

        assertEquals(List.of(3), results);
    }

    @Test
    void genericRetryUtility() {
        Integer apply = GenericRetryUtility.apply(
                x -> x - 1,
                r -> r <= 10,
                11,
                1
        );

        assertEquals(10, apply);
    }

    @Test
    void genericRetryUtilityShouldThrowException() {
        assertThrows(RuntimeException.class, () -> GenericRetryUtility.apply(
                x -> x - 1,
                r -> r == 2,
                11,
                2
        ));
    }

    @Test
    void frequencySort() {
        var list = new ArrayList<>(List.of("hello", "world", "as", "as", "as", "hello", "words"));
        FrequencySort.sort(list);

        assertEquals(List.of("as", "as", "as", "hello", "hello", "world", "words"), list);
    }

    @Test
    void dynamicSortingUtility() {
        var map = DynamicSortingUtility.sort(
                List.of(1, generatePerson(2, "ann"), "temp", 3, 2, "a", generatePerson(1, "joe")),
                Map.of(PersonGenerator.Person.class, Comparator.comparing(PersonGenerator.Person::name))
        );

        assertEquals(Map.of(
                        PersonGenerator.Person.class, List.of(PersonGenerator.generatePerson(2, "ann"), PersonGenerator.generatePerson(1, "joe")),
                        String.class, List.of("a", "temp"),
                        Integer.class, List.of(1, 2, 3)),
                map);
    }

    // Top-N Finder
    @Test
    void topNFinder() {
        assertEquals(
                List.of(3, 2),
                TomNFinder.getTopN()
                        .apply(List.of(3, 2, 1), 2));
    }
}

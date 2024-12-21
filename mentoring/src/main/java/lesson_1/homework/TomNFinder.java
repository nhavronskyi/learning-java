package lesson_1.homework;

import java.util.Collection;
import java.util.function.BiFunction;

public class TomNFinder {
    /**
     * Top-N Finder: Find the top N elements in a collection dynamically.
     *
     * @return {@link Collection} of any top n elements
     */
    public static BiFunction<Collection<?>, Integer, Collection<?>> getTopN() {
        return (collection, number) -> collection.stream()
                .limit(number)
                .toList();
    }
}

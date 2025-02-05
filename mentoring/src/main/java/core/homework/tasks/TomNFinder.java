package core.homework.tasks;

import java.util.Collection;
import java.util.function.BiFunction;

public interface TomNFinder {
    /**
     * <p>Top-N Finder: Find the top N elements in a collection dynamically.<p/>
     * <p>NOTE: I know that there are no sense of doing that using {@link java.util.function.Function},
     * but I wanted to do that funny:3</p>
     *
     * @return {@link Collection} of any top n elements
     */
    static BiFunction<Collection<?>, Integer, Collection<?>> getTopN() {
        return (collection, number) -> collection.stream()
                .limit(number)
                .toList();
    }
}

package lesson_1.homework.tasks;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface DynamicSortingUtility {
    /**
     * <p>Dynamic Sorting Utility:
     * Create a utility that dynamically sorts a list of objects based on runtime-provided field names and directions.</p>
     *
     * @return sorted map
     */
    static <T> Map<Class<?>, List<T>> sort(List<T> list, Map<Class<?>, Comparator<?>> sortBy) {
        return list.stream()
                .collect(Collectors.groupingBy(
                        Object::getClass,
                        Collectors.mapping(
                                obj -> obj,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        objects -> comparator(sortBy, objects)
                                )
                        )
                ));
    }

    private static <T> List<T> comparator(Map<Class<?>, Comparator<?>> sortBy, List<T> objects) {
        Comparator<?> comparator = sortBy.getOrDefault(
                objects.getFirst().getClass(),
                Comparator.naturalOrder()
        );
        return objects.stream()
                .sorted((Comparator<? super T>) comparator)
                .toList();
    }
}

package lesson_1.homework.tasks;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface FrequencySort {
    // Frequency Sort: Write a method that sorts a list of strings by their frequency (most frequent first).
    static void sort(List<String> list) {
        Map<String, Long> frequencyMap = list.stream()
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(
                        word -> word,
                        Collectors.counting()
                ));

        list.sort((o1, o2) -> frequencyMap.get(o2).compareTo(frequencyMap.get(o1)));
    }
}

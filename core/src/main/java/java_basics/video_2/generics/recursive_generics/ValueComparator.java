package java_basics.video_2.generics.recursive_generics;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ValueComparator<T extends Comparable<T>> {
    private T value;

    public int compare(T o) {
        return value.compareTo(o);
    }
}

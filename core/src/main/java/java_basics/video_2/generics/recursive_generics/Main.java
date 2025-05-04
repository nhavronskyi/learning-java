package java_basics.video_2.generics.recursive_generics;

public class Main {
    public static void main(String[] args) {
        ValueComparator<Integer> valueComparator = new ValueComparator<>(25);

        System.out.println(valueComparator.compare(5));
    }
}

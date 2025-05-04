package java_basics.video_2.generics;

public class Main {
    public static void main(String[] args) {
        Container<String> container = new Container<>("Hello");
        System.out.println(container.value());

        Container<Integer> integerContainer = new Container<>(10);
        System.out.println(integerContainer.value());
    }
}

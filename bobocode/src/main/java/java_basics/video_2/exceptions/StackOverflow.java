package java_basics.video_2.exceptions;

public class StackOverflow {
    @SuppressWarnings("InfiniteRecursion")
    public static void main(String[] args) {
        main(args);
    }
}

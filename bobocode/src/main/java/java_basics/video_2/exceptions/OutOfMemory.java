package java_basics.video_2.exceptions;

import java.util.ArrayList;

public class OutOfMemory {
    public static void main(String[] args) {
        ArrayList<?> list = new ArrayList<>();
        while (true) {
            list.add(null);
        }
    }
}

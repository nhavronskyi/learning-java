package java_basics.video_2.exceptions;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

@Slf4j
public class OutOfMemory {
    @SuppressWarnings("InfiniteLoopStatement")
    public static void main(String[] args) {
        ArrayList<?> list = new ArrayList<>();
        while (true) {
            list.add(null);
            log.info("List size: {}", list.size());
        }
    }
}

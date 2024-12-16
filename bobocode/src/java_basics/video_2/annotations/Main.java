package java_basics.video_2.annotations;

import java_basics.video_2.annotations.annotations.Marker;
import java_basics.video_2.annotations.annotations.MethodMarker;
import java_basics.video_2.annotations.annotations.RuntimeMarker;

import java.util.Arrays;

@Marker
@RuntimeMarker(value = "here is some message from annotation")
// @MethodMarker -> showing error because target is only method
public class Main {
    public static void main(String[] args) {
        // we can get all annotations using
        var annotations = Main.class.getAnnotations();
        Arrays.stream(annotations)
                .forEach(System.out::println);


        // we can process info from annotation
        var anno = Main.class.getAnnotation(RuntimeMarker.class);
        System.out.println("value: " + anno.value());
        System.out.println("default desc: " + anno.description());
    }

    @MethodMarker
    public static void magicMethod() {
    }
}

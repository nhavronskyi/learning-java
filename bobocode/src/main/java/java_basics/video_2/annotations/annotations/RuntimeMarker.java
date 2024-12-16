package java_basics.video_2.annotations.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RuntimeMarker {
    String value();

    String description() default "here is some description";
}

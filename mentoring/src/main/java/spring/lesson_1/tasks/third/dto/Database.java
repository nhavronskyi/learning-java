package spring.lesson_1.tasks.third.dto;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.lang.Nullable;

public interface Database extends Condition {
    void connect();

    @Override
    default boolean matches(ConditionContext context, @Nullable AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("db.mock", Boolean.class, false);
    }

}

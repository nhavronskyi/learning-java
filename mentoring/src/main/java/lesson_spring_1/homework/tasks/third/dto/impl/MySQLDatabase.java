package lesson_spring_1.homework.tasks.third.dto.impl;

import lesson_spring_1.homework.tasks.third.dto.Database;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class MySQLDatabase implements Database {
    @Override
    public void connect() {
        log.debug("Connected to MySQL database");
    }

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return !Database.super.matches(context, metadata);
    }
}

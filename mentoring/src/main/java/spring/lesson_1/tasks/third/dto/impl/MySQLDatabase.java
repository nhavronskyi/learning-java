package spring.lesson_1.tasks.third.dto.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import spring.lesson_1.tasks.third.dto.Database;

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

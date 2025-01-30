package lesson_spring_1.homework.tasks.third.dto.impl;

import lesson_spring_1.homework.tasks.third.dto.Database;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockDatabase implements Database {
    @Override
    public void connect() {
        log.debug("Connected to mock database");
    }
}

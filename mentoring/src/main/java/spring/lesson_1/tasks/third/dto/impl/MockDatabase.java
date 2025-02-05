package spring.lesson_1.tasks.third.dto.impl;

import lombok.extern.slf4j.Slf4j;
import spring.lesson_1.tasks.third.dto.Database;

@Slf4j
public class MockDatabase implements Database {
    @Override
    public void connect() {
        log.debug("Connected to mock database");
    }
}

package lesson_spring_1.homework.tasks.third.repository;

import lesson_spring_1.homework.tasks.third.dto.Database;

public record UserRepository(Database database) {
    public void performDatabaseOperation() {
        database.connect();
    }
}

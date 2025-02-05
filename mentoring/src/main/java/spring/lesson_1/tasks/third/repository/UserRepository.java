package spring.lesson_1.tasks.third.repository;


import spring.lesson_1.tasks.third.dto.Database;

public record UserRepository(Database database) {
    public void performDatabaseOperation() {
        database.connect();
    }
}

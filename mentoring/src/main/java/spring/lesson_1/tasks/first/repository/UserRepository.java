package spring.lesson_1.tasks.first.repository;

import lombok.Getter;
import spring.lesson_1.tasks.first.dto.Database;

@Getter
public class UserRepository {
    private final Database db;

    public UserRepository(Database db) {
        this.db = db;
    }


}

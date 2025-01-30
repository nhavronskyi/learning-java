package lesson_spring_1.homework.tasks.first.repository;

import lesson_spring_1.homework.tasks.first.dto.Database;
import lombok.Getter;

@Getter
public class UserRepository {
    private final Database db;

    public UserRepository(Database db) {
        this.db = db;
    }


}

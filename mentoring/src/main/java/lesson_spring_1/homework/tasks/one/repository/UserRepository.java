package lesson_spring_1.homework.tasks.one.repository;

import lesson_spring_1.homework.tasks.one.dtos.Database;
import lombok.Getter;

@Getter
public class UserRepository {
    private final Database db;

    public UserRepository(Database db) {
        this.db = db;
    }


}

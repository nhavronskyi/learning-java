package databases.lesson_1.homework.repository;

import databases.lesson_1.homework.dto.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    @Query("SELECT COALESCE(MAX(o.id), 0) FROM UserOrder o")
    Optional<Long> findMaxId();

    Optional<List<UserOrder>> findAllByUserId(Long userId);
}

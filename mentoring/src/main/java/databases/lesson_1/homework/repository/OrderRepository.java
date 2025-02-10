package databases.lesson_1.homework.repository;

import databases.lesson_1.homework.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT COALESCE(MAX(o.id), 0) FROM Order o")
    Optional<Long> findMaxId();
}

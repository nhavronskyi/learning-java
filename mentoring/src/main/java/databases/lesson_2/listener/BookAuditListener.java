package databases.lesson_2.listener;

import databases.lesson_2.dto.Book;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;

public class BookAuditListener {
    @PrePersist
    public void prePersist(Book book) {
        LocalDateTime now = LocalDateTime.now();
        book.setCreatedAt(now);
        book.setUpdatedAt(now);
    }

    @PreUpdate
    public void preUpdate(Book book) {
        book.setUpdatedAt(LocalDateTime.now());
    }
}

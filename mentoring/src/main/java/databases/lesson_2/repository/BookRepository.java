package databases.lesson_2.repository;

import databases.lesson_2.dto.Book;

import java.util.List;

public interface BookRepository {
    List<Book> searchBooks(String titlePart, Long publisherId);

    default List<Book> searchBooks(String titlePart) {
        return searchBooks(titlePart, null);
    }

    default List<Book> searchBooks(Long publisherId) {
        return searchBooks(null, publisherId);
    }

    default List<Book> searchBooks() {
        return searchBooks(null, null);
    }
}

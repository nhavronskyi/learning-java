package databases.lesson_2.repository;

import databases.lesson_2.dto.Book;
import databases.lesson_2.dto.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ComponentScan("databases.lesson_2.repository")
class BookRepositoryTest {
    private final Publisher publisher1 = getPublisher("Publisher One");
    private final Publisher publisher2 = getPublisher("Publisher Two");
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        entityManager.persist(publisher1);
        entityManager.persist(publisher2);

        entityManager.persist(getBook("Spring in Action", publisher1));
        entityManager.persist(getBook("Hibernate in Action", publisher1));
        entityManager.persist(getBook("Java Concurrency in Practice", publisher2));

        entityManager.flush();
    }

    @Test
    void testSearchBooksByPartialTitle() {
        List<Book> books = bookRepository.searchBooks("Action");
        assertEquals(2, books.size());
    }

    @Test
    void testSearchBooksByPublisherId() {
        List<Book> books = bookRepository.searchBooks(publisher1.getId());
        assertEquals(2, books.size());
    }

    @Test
    void testSearchBooksByTitleAndPublisherId() {
        List<Book> books = bookRepository.searchBooks("Java", publisher2.getId());
        assertEquals(1, books.size());
    }

    @Test
    void testSearchBooksWithNoParameters() {
        List<Book> books = bookRepository.searchBooks();
        assertEquals(3, books.size());
    }

    private Publisher getPublisher(String name) {
        var publisher = new Publisher();
        publisher.setName(name);
        return publisher;
    }

    private Book getBook(String title, Publisher publisher) {
        Book book1 = new Book();
        book1.setTitle(title);
        book1.setPublisher(publisher);
        return book1;
    }
}

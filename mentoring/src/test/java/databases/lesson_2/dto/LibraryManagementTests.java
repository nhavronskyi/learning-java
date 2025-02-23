package databases.lesson_2.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class LibraryManagementTests {
    @Autowired
    private TestEntityManager entityManager;

    @ParameterizedTest
    @CsvSource({
            "J.K. Rowling, Bloomsbury, Harry Potter",
            "J.R.R. Tolkien, HarperCollins, The Lord of the Rings",
            "George R.R. Martin, Bantam Books, A Game of Thrones"
    })
    void testBookAuthorPublisherRelationships(String authorName, String publisherName, String bookName) {
        Author author = new Author();
        author.setName(authorName);
        entityManager.persist(author);

        Publisher publisher = new Publisher();
        publisher.setName(publisherName);
        entityManager.persist(publisher);

        Book book = new Book();
        book.setTitle(bookName);
        book.setAuthor(author);
        book.setPublisher(publisher);
        entityManager.persist(book);

        entityManager.flush();

        var b = entityManager.find(Book.class, book.getId());
        var a = entityManager.find(Author.class, author.getId());
        var p = entityManager.find(Publisher.class, publisher.getId());
        assertEquals(bookName, b.getTitle());
        assertEquals(authorName, a.getName());
        assertEquals(publisherName, p.getName());
    }

    @Test
    void testAddBook() {
        Author author = new Author();
        author.setName("Test Author");
        entityManager.persist(author);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor(author);
        entityManager.persist(book);

        author.addBook(book);
        entityManager.flush();

        var a = entityManager.find(Author.class, author.getId());
        var b = entityManager.find(Book.class, book.getId());

        assertEquals(1, a.getBooks().size());
        assertEquals(author, b.getAuthor());
    }

    @Test
    void testRemoveBook() {
        Author author = new Author();
        author.setName("Test Author");
        entityManager.persist(author);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor(author);
        entityManager.persist(book);

        author.addBook(book);
        entityManager.flush();

        author.removeBook(book);
        entityManager.flush();

        var a = entityManager.find(Author.class, author.getId());
        var b = entityManager.find(Book.class, book.getId());

        assertEquals(0, a.getBooks().size());
        assertNull(b.getAuthor());
    }

    @Test
    void testBookAuditing() {
        Book book = new Book();
        book.setTitle("Test Book");
        entityManager.persist(book);
        entityManager.flush();

        assertNotNull(book.getCreatedAt());
        assertNotNull(book.getUpdatedAt());

        LocalDateTime initialModifiedDate = book.getUpdatedAt();
        book.setTitle("Updated Title");
        entityManager.persist(book);
        entityManager.flush();

        assertNotEquals(initialModifiedDate, book.getUpdatedAt());
    }
}

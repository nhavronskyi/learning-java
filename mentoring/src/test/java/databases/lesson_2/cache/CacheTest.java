package databases.lesson_2.cache;

import databases.lesson_2.HomeworkApplication;
import databases.lesson_2.config.CacheConfig;
import databases.lesson_2.dto.Book;
import databases.lesson_2.repository.BookRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = {
                CacheAutoConfiguration.class, HomeworkApplication.class, CacheConfig.class
        }
)
@ComponentScan("databases.lesson_2.repository")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CacheTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void testCacheManager() {
        assertNotNull(cacheManager);
        Cache cache = cacheManager.getCache("books");
        assertNotNull(cache);
    }

    // TODO: Fix that test
    @Test
    @Disabled("Ignoring temporarily because it doesn't work as expected")
    void testBookCaching() {
        Book book1 = new Book();
        book1.setTitle("Test Book 1");
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("Test Book 2");
        bookRepository.save(book2);

        Cache cache = cacheManager.getCache("books");
        assertNotNull(cache);
        Cache.ValueWrapper cachedValue = cache.get("books");

        assertNotNull(cachedValue, "Cache is empty after first query");

        List<Book> firstCall = bookRepository.searchBooks();
        assertNotNull(firstCall);
        assertEquals(2, firstCall.size());

        assertNotNull(cache.get(""));

        List<Book> secondCall = bookRepository.searchBooks();
        assertEquals(firstCall.size(), secondCall.size());
    }
}

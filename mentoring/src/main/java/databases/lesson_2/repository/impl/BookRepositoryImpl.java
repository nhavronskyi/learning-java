package databases.lesson_2.repository.impl;

import databases.lesson_2.dto.Book;
import databases.lesson_2.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public List<Book> searchBooks(String titlePart, Long publisherId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);

        Root<Book> book = query.from(Book.class);
        query.select(book)
                .where(getSearchPredicates(titlePart, publisherId, cb, book));
        return entityManager.createQuery(query).getResultList();
    }

    private Predicate[] getSearchPredicates(String titlePart, Long publisherId, CriteriaBuilder cb, Root<Book> book) {
        List<Predicate> predicates = new ArrayList<>();
        Optional.ofNullable(titlePart)
                .filter(s -> !s.isEmpty())
                .ifPresent(title ->
                        predicates.add(cb.like(cb.lower(book.get("title")), "%%" + title.toLowerCase() + "%%")));

        Optional.ofNullable(publisherId)
                .ifPresent(id ->
                        predicates.add(cb.equal(book.get("publisher").get("id"), id)));
        return predicates.toArray(Predicate[]::new);
    }
}

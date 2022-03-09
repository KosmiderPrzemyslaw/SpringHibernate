package pl.kosmider.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.kosmider.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    EntityManager entityManager;

    public void saveBook(Book book) {
        entityManager.persist(book);
    }

    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        entityManager.merge(book);
    }

    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ?
                book : entityManager.merge(book));
    }

    public List<Book> getAllBooksFromDbOrderByRating() {
        Query query = entityManager.createQuery("SELECT b from Book b order by b.rating");

        return (List<Book>) query.getResultList();
    }

    public List<Book> getAllBooksWithPublisher() {
        Query query = entityManager.createQuery("SELECT b from Book  b join b.publisher");

        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public List<Book> getAllBooksWithAuthor() {
        Query query = entityManager.createQuery("SELECT b from Book b join b.authors");
        List resultList = query.getResultList();
        return resultList;
    }

    public List<Book> getAllBooksWithAuthorUsingFetch() {
        Query query = entityManager.createQuery("select b from Book b join fetch b.authors");
        List<Book> resultList = query.getResultList();

        for (Book b : resultList
        ) {
            findBookWithAuthorsById(b.getId());
        }
        return resultList;
    }

    public Book findBookWithAuthorsById(Long id) {
        Book book = findById(id);
        Hibernate.initialize(book.getAuthors());
        return book;
    }

    public List<Book> getAllBooksFromDb() {
        Query query = entityManager.createQuery("select b from Book b");
        List<Book> resultList = query.getResultList();
        return resultList;
    }

}

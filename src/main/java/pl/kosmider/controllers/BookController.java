package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.AuthorDao;
import pl.kosmider.dao.BookDao;
import pl.kosmider.dao.PublisherDao;
import pl.kosmider.entity.Author;
import pl.kosmider.entity.Book;
import pl.kosmider.entity.Publisher;

import java.util.List;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @RequestMapping("/book/add")
    @ResponseBody
    public String hello() {
        Book book = new Book();
        book.setTitle("Nad niemnem");


        Author author1 = authorDao.findAuthorById(1);
        Author author2 = authorDao.findAuthorById(2);

        book.setAuthors(List.of(author1, author2));

        Publisher publisher = new Publisher();
        publisher.setName("wiosna");
        publisherDao.savePublisher(publisher);
        book.setPublisher(publisher);
        bookDao.saveBook(book);

        return book.toString();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookDao.findById(id);
        book.setTitle(title);
        bookDao.update(book);

        return book.toString();
    }

    @RequestMapping("book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        Book book = bookDao.findById(id);
        bookDao.delete(book);
        return "deleted";
    }

    @RequestMapping("allBooks")
    @ResponseBody
    public String books() {


        List<Book> allBooksFromDb = bookDao.getAllBooksFromDbOrderByRating();
        allBooksFromDb.forEach(System.out::println);

        return allBooksFromDb.toString();
    }

    @RequestMapping("booksWithPublisher")
    @ResponseBody
    public String booksWithPublisher() {
        List<Book> allBooksWithPublisher = bookDao.getAllBooksWithPublisher();
        allBooksWithPublisher.forEach(book -> System.out.println(book.toString()));
        return allBooksWithPublisher.toString();
    }

    @RequestMapping("booksWithAuthor")
    @ResponseBody
    public String booksWithAuthor() {
        List<Book> allBooksWithAuthor = bookDao.getAllBooksWithAuthor();
        allBooksWithAuthor.forEach(book -> System.out.println(book.toString()));
        return allBooksWithAuthor.toString();
    }

    @RequestMapping("booksWithAuthorFetch")
    @ResponseBody
    public List<Book> getBooksWithAuthorUsingFetch(){
        List<Book> allBooksWithAuthorUsingFetch = bookDao.getAllBooksWithAuthorUsingFetch();
        for (Book b: allBooksWithAuthorUsingFetch
             ) {
            System.out.println(b.toString());
        }
                return allBooksWithAuthorUsingFetch;
    }
}

package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.kosmider.dao.AuthorDao;
import pl.kosmider.dao.BookDao;
import pl.kosmider.dao.PublisherDao;
import pl.kosmider.entity.Author;
import pl.kosmider.entity.Book;
import pl.kosmider.entity.Publisher;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final Validator validator;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, Validator validator) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.validator = validator;
    }

    @GetMapping("/book/add")
    public String hello(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "addBookForm";
    }

    @PostMapping("/book/add")
    public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "addBookForm";
        } else {
            bookDao.saveBook(book);
        }
        return "addBookForm";
    }

    @GetMapping("/allBooks")
    public String showAllBooks(){
        return "/allBooks";
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

    @GetMapping("/selectBookToUpdate")
    public String selectBookToUpdate(Model model) {

        model.addAttribute("book", new Book());

        return "updateBook";
    }

    @PostMapping("/selectBookToUpdate")
    public String selectBookToUpdate(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/updateBook";
        } else {
            System.out.println("eureka");
        }
        Book bookToUpdate = bookDao.findById(book.getId());

        return "/updateBook";
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
    public List<Book> getBooksWithAuthorUsingFetch() {
        List<Book> allBooksWithAuthorUsingFetch = bookDao.getAllBooksWithAuthorUsingFetch();
        for (Book b : allBooksWithAuthorUsingFetch
        ) {
            System.out.println(b.toString());
        }
        return allBooksWithAuthorUsingFetch;
    }

    @ModelAttribute
    public List<Publisher> publisherList() {
        List<Publisher> allPublishers = publisherDao.getAllPublishers();
        return allPublishers;
    }

    @ModelAttribute
    public List<Author> authorList() {
        List<Author> authorList = authorDao.getAllAuthors();
        return authorList;
    }

    @ModelAttribute
    public List<Book> boo() {
        List<Book> allBooksFromDb = bookDao.getAllBooksFromDb();
        return allBooksFromDb;
    }
}

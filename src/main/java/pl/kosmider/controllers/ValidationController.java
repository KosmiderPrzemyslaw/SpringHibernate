package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.AuthorDao;
import pl.kosmider.dao.BookDao;
import pl.kosmider.dao.PublisherDao;
import pl.kosmider.entity.Book;
import pl.kosmider.entity.Person;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;

@Controller
public class ValidationController {


    private final BookDao bookDao;
    private final Validator validator;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public ValidationController(BookDao bookDao, Validator validator, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.validator = validator;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @RequestMapping("/validate")
    @ResponseBody
    public String validateTest() {
        Person p2 = new Person();
        Set<ConstraintViolation<Person>> violations = validator.validate(p2);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Person> constraintViolation : violations) {
                System.out.println(constraintViolation.getPropertyPath() + " "
                        + constraintViolation.getMessage());
            }
        } else {
// save object
        }
        return "validateResult";
    }

    @GetMapping("validateBook")
    public String validateBook(Model model) {
        Book book = new Book();
        book.setPages(0);
        book.setRating(BigDecimal.valueOf(0));
        book.setAuthors(null);
        book.setPublisher(null);
        // book.setPublisher(publisherDao.findPublisherById(7));
        //book.setAuthor(authorDao.findAuthorById(1).getFirstName());
        //book.setRating(BigDecimal.valueOf(2));
        book.setTitle("O startm drzewie");

        Set<ConstraintViolation<Book>> violations = validator.validate(book);


        if (!violations.isEmpty()) {
            for (ConstraintViolation<Book> constraintViolation : violations) {
                System.out.println(constraintViolation.getPropertyPath() + " " +
                        constraintViolation.getMessage());
            }
        } else {
            bookDao.saveBook(book);
        }

        model.addAttribute("violations", violations);
        return "/validate";
    }


}
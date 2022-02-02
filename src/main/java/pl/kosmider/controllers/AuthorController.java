package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.AuthorDao;
import pl.kosmider.entity.Author;

@Controller
public class AuthorController {
    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @RequestMapping("author/add")
    @ResponseBody
    public String addAuthor() {
        Author author = new Author();
        author.setFirstName("Marcin");
        author.setLastName("Kokos");
        authorDao.saveAuthor(author);

        return author.toString();
    }

    @RequestMapping("author/get/{id}")
    @ResponseBody
    public String findById(@PathVariable long id) {
        Author author = authorDao.findAuthorById(id);
        return author.toString();
    }

    @RequestMapping("author/update/{id}")
    @ResponseBody
    public String update(@PathVariable long id) {
        Author author = authorDao.findAuthorById(id);
        author.setLastName("dolittle");
        author.setFirstName("doktor");
        authorDao.update(author);

        return author.toString();
    }

    @RequestMapping("author/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable long id) {
        authorDao.delete(authorDao.findAuthorById(id));
        return "deleted";
    }
}

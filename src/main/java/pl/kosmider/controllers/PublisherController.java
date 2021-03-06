package pl.kosmider.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.PublisherDao;
import pl.kosmider.entity.Publisher;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
public class PublisherController {

    private final PublisherDao publisherDao;
    private final Validator validator;

    public PublisherController(PublisherDao publisherDao, Validator validator) {
        this.publisherDao = publisherDao;
        this.validator = validator;
    }

    @RequestMapping("publisher/add")
    @ResponseBody
    public String create() {
        Publisher publisher = new Publisher();
        publisher.setName("SOWA");
        publisherDao.savePublisher(publisher);

        return publisher.toString();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String findPublisherById(@PathVariable long id) {
        Publisher publisher = publisherDao.findPublisherById(id);
        return publisher.toString();
    }

    @RequestMapping("publisher/update/{id}/{name}")
    @ResponseBody
    public String updatePublisher(@PathVariable long id, @PathVariable String name) {
        Publisher publisher = publisherDao.findPublisherById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("publisher/delete/{id}")
    @ResponseBody
    public String deletePublisher(@PathVariable long id) {
        publisherDao.delete(publisherDao.findPublisherById(id));
        return "deleted";
    }

    @RequestMapping("allPublishers")
    @ResponseBody
    public String getAllPublishers() {
        List<Publisher> allPublishers = publisherDao.getAllPublishers();
        allPublishers.forEach(System.out::println);

        return allPublishers.toString() + "\n";
    }

    @RequestMapping("/addPublisher")
    public String addPublisher(Model model) {
        Publisher publisher = new Publisher("Znaki", "sad", "a");
        Set<ConstraintViolation<Publisher>> violations = validator.validate(publisher);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Publisher> constraintViolation : violations
            ) {
                System.out.println(constraintViolation.getPropertyPath() + " " + constraintViolation.getMessage());
            }

        } else {
            publisherDao.savePublisher(publisher);
        }
        model.addAttribute("violations", violations);
        return "/publisherAfterValid";
    }
}

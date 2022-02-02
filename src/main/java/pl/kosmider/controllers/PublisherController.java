package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.PublisherDao;
import pl.kosmider.entity.Publisher;

import java.util.List;

@Controller
public class PublisherController {
    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
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
    public List<Publisher> getAllPublishers(){
        List<Publisher> allPublishers = publisherDao.getAllPublishers();
        allPublishers.forEach(System.out::println);

        return allPublishers;
    }
}

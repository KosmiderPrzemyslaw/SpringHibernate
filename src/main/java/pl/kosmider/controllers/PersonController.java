package pl.kosmider.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.kosmider.dao.PersonDao;
import pl.kosmider.dao.PersonDetailsDao;
import pl.kosmider.entity.Person;
import pl.kosmider.entity.PersonDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class PersonController {
    private final PersonDao personDao;
    private final PersonDetailsDao personDetailsDao;

    public PersonController(PersonDao personDao, PersonDetailsDao personDetailsDao) {
        this.personDao = personDao;
        this.personDetailsDao = personDetailsDao;
    }

    @RequestMapping("/person/add")
    @ResponseBody
    public String addPerson(){
        PersonDetails personDetails = new PersonDetails();

        personDetails.setCity("Knurow");
        personDetails.setFirstName("Jurek");
        personDetails.setLastName("Ogar");
        personDetails.setStreet("zieolna");
        personDetails.setStreetNumber(2);

        personDetailsDao.savePersonDetails(personDetails);
        Person person = new Person();
        person.setEmail("lol@");
        person.setLogin("login");
        person.setPassword("password");
        person.setPersonDetails(personDetails);

        personDao.savePerson(person);

        return person.toString();
    }

    @ModelAttribute("skills")
    public Collection<String> skills(){
        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("c++");
        skills.add("ruby");
        return  skills;
    }

    @GetMapping("/showAllSkills")
    public String showSkills(){
        return "skills";
    }
}

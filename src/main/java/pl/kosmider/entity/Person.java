package pl.kosmider.entity;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.xml.transform.Source;

@Entity
@Table(name = "person")
public class Person implements Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @PESEL
    @Column(name = "email", unique = true)
    private String email;

    @OneToOne
    @JoinColumn(name = "personDetails_id", unique = true)
    private PersonDetails personDetails;

    public Person(String login, String password, String email, PersonDetails personDetails) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.personDetails = personDetails;
    }

    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", personDetails=" + personDetails +
                '}';
    }

    @Override
    public void setSystemId(String s) {

    }

    @Override
    public String getSystemId() {
        return null;
    }
}

package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RestController
@Scope(value = "session")
@Component(value = "indexController")
public class IndexController {

    @Autowired
    PersonRepository personRepository;

    @Getter
    private String message;

    @Getter
    private List<Person> personList;

    @PostConstruct
    public void init() {
        populatePersonListFromDatabase();
    }

    public void sayHelloWorld() {
        message = "HELLO WORLD!";
    }

    public void addTestPersonToDatabase() {
        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Foobar");
        //p.setBirthDate(LocalDate.of(1988, 9, 10));
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(1988, Calendar.SEPTEMBER, 10);
        p.setBirthDate(birthDate.getTime());
        p.setPhoneNumber("+55 (48) 98802-0413");
        p.setAddress("Rainbow Road, 777");

        //personRepository.insertWithNativeQuery(p);
        personRepository.insertWithEntityManager(p);

        populatePersonListFromDatabase();
    }

    public void populatePersonListFromDatabase() {
        personList = personRepository.getAll();
    }
}

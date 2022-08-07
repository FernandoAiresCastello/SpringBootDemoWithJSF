package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

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
        reloadPersonList();
    }

    public String addTestPersonToDatabase() {
        Person p = new Person();
        p.setFirstName("John");
        p.setLastName("Foobar");
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(1988, Calendar.SEPTEMBER, 10);
        p.setBirthDate(birthDate.getTime());
        p.setPhoneNumber("+55 (48) 98802-0413");
        p.setAddress("Rainbow Road, 777");

        personRepository.insertWithEntityManager(p);
        reloadPersonList();

        return "/index.xhtml?faces-redirect=true";
    }

    public String deletePersonFromDatabase() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ctx = fc.getExternalContext();
        Map<String, String> params = ctx.getRequestParameterMap();
        int idPerson = Integer.parseInt(params.get("idPerson"));

        personRepository.delete(idPerson);
        reloadPersonList();

        return "/index.xhtml?faces-redirect=true";
    }

    public void reloadPersonList() {
        personList = personRepository.getAll();
    }
}

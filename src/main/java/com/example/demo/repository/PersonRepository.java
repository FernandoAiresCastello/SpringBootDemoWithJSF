package com.example.demo.repository;

import com.example.demo.model.Person;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private static final Log log = LogFactory.getLog(PersonRepository.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void insertWithNativeQuery(Person person) {
        em.createNativeQuery("INSERT INTO person (first_name, last_name, birth_date, phone_number, address) VALUES (?,?,?,?,?)")
                .setParameter(1, person.getFirstName())
                .setParameter(2, person.getLastName())
                .setParameter(3, person.getBirthDate())
                .setParameter(4, person.getPhoneNumber())
                .setParameter(5, person.getAddress())
                .executeUpdate();
    }

    @Transactional
    public void insertWithEntityManager(Person person) {
        try {
            em.persist(person);
        } catch (Exception ex) {
            log.error("Could not persist Person", ex);
        }
    }

    public List<Person> getAll() {
        List<Person> personList = new ArrayList<>();
        try {
            TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
            personList = query.getResultList();
        } catch (Exception ex) {
            log.error("Could not get Person list", ex);
        }
        return personList;
    }
}

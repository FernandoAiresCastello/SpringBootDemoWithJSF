package com.example.demo.model;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "Person")
@Table(schema = "public", name = "person")
public class Person implements Serializable {

    private static final Log log = LogFactory.getLog(Person.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(mappedBy = "person")
    private UserLogin userLogin;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Transient
    private String fullName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @PrePersist
    public void onPrePersist() {
        log.info("Attempting to persist new Person...");
    }

    @PostPersist
    public void onPostPersist() {
        log.info("New Person has been persisted with id " + id);
    }

    @PostLoad
    public void onPostLoad() {
        fullName = firstName + " " + lastName;
    }
}

package com.example.demo.model;

import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "UserLogin")
@Table(schema = "public", name = "user_login")
public class UserLogin implements Serializable {

    private static Log log = LogFactory.getLog(UserLogin.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_person", referencedColumnName = "id")
    private Person person;

    @NotNull
    @Column(name = "username")
    private String userName;

    @NotNull
    @Column(name = "password")
    private String password;

    @Column(name = "last_login_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime lastLoginDatetime;

    @Column(name = "is_active")
    private Boolean isActive;

    @PrePersist
    public void onPrePersist() {
        log.info("Attempting to persist new UserLogin...");
    }

    @PostPersist
    public void onPostPersist() {
        log.info("New UserLogin has been persisted with id " + id);
    }
}

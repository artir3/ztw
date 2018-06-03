package com.arma.ztw.restrezerv.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class Users implements Serializable {

    @Id
    @Column
    private Long id;

    @Column(columnDefinition = "text")
    private String name;

    @Column(columnDefinition = "text")
    private String surname;

    @Column(columnDefinition = "text")
    private String email;

    @Column(columnDefinition = "text")
    private String city;

    @Column(columnDefinition = "text")
    private String password;

    public Users(String name, String surname, String email, String city, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.city = city;
        this.password = password;
    }
}

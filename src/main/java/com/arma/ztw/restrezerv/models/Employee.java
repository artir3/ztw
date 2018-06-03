package com.arma.ztw.restrezerv.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @Column
    private Long id;

    @Column()
    private String permissions;

    @ManyToOne
    private Users user;

}

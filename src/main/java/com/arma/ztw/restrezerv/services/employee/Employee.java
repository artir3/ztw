package com.arma.ztw.restrezerv.services.employee;

import com.arma.ztw.restrezerv.services.users.Users;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column()
    private String permissions;

    @ManyToOne
    private Users user;

}

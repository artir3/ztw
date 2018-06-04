package com.arma.ztw.restrezerv.services.users;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO implements Serializable {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String city;

}
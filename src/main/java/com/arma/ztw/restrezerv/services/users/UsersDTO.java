package com.arma.ztw.restrezerv.services.users;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
@ToString
public class UsersDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String password;
    private String authorisationToken;

}

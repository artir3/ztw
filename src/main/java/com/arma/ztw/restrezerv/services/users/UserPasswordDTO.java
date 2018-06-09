package com.arma.ztw.restrezerv.services.users;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize
public class UserPasswordDTO {
    private Long id;
    private String oldPassword;
    private String password;
}

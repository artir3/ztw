package com.arma.ztw.restrezerv.services.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {

    @Query
    Users findByEmail(String email);
}

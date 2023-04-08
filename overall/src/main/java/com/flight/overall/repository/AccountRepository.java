package com.flight.overall.repository;

import com.flight.overall.entity.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("SELECT a " +
            " FROM Account a " +
            "WHERE a.username =:username")
    Account findByUsername(@Param("username") String username);

}

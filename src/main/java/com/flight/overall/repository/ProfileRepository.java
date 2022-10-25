package com.flight.overall.repository;

import com.flight.overall.entity.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    Optional<Profile> findByUsername(String username);

    @Query("SELECT p" +
            " FROM Profile p " +
            "WHERE p.id =:id")
    Profile findProfile(@Param("id") Long id);
}

package com.flight.overall.repository;

import com.flight.overall.entity.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */

public interface ProfileRepository extends CrudRepository<Profile, Long> {

    @Query("SELECT p " +
            " FROM Profile p ")
    List<Profile> findAllProfiles();

    Optional<Profile> findByUsername(String username);
}

package com.flight.overall.repository;

import com.flight.overall.entity.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author FLIGHT
 * @date 22.10.2022
 */
public interface RatingRepository extends CrudRepository<Rating, Long> {

    @Query("SELECT r " +
            " FROM Rating r " +
            "WHERE r.profile.id =:profileId " +
            "ORDER BY r.category.id")
    List<Rating> getProfileRatings(@Param("profileId") long profileId);
}

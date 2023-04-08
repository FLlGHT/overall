package com.flight.overall.repository;

import com.flight.overall.entity.GroupRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroupRatingRepository extends CrudRepository<GroupRating, Long> {

    @Query("SELECT gr " +
            " FROM GroupRating gr " +
            "WHERE gr.profile.id =:profileId " +
            "ORDER BY gr.categoryGroup.id")
    List<GroupRating> getProfileGroupRatings(@Param("profileId") long profileId);


    @Query("SELECT r.groupRating " +
            " FROM Rating r " +
            "WHERE r.id = :ratingId")
    Optional<GroupRating> findGroupRatingByRating(@Param("ratingId") long ratingId);
}

package com.flight.overall.repository;

import com.flight.overall.entity.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

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

    @Query("SELECT r " +
            " FROM Rating r" +
            " JOIN r.category c " +
            "WHERE r.profile.id =:profileId" +
            "  AND c.categoryType <> 'NOT_AFFECT' " +
            "ORDER BY r.category.id")
    List<Rating> getAffectsRatings(@Param("profileId") long profileId);

    @Query("SELECT r " +
            " FROM Rating r " +
            "WHERE r.category.id =:categoryId" +
            "  AND r.profile.id =:profileId")
    Optional<Rating> findProfileRatingByCategory(@Param("profileId") long profileId,
                                                 @Param("categoryId") long categoryId);

    @Query("SELECT r " +
            " FROM Rating r " +
            "WHERE r.profile.id =:profileId" +
            "  AND r.groupRating.id =:groupId")
    List<Rating> findRatingsFromGroup(@Param("profileId") long profileId,
                                      @Param("groupId") long groupId);
}

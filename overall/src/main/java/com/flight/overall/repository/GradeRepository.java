package com.flight.overall.repository;

import com.flight.overall.entity.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query("SELECT g " +
            " FROM Grade g" +
            " JOIN g.rating r " +
            "WHERE g.account.id =:accountId" +
            "  AND r.profile.id =:profileId")
    List<Grade> findAllAccountGrades(@Param("accountId") long accountId,
                                     @Param("profileId") long profileId);

    @Query("SELECT g " +
            " FROM Grade g " +
            "WHERE g.id =:id")
    Grade findGrade(@Param("id") long id);

}

package com.flight.overall.repository;

import com.flight.overall.entity.Grade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {

    @Query("SELECT g " +
            " FROM Grade g " +
            "WHERE g.account.id =:accountId")
    List<Grade> findAllAccountGrades(@Param("accountId") long accountId);

    @Query("SELECT g " +
            " FROM Grade g " +
            "WHERE g.id =:id")
    Grade findGrade(@Param("id") long id);

}

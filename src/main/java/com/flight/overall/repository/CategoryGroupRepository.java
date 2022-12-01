package com.flight.overall.repository;

import com.flight.overall.entity.CategoryGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryGroupRepository extends CrudRepository<CategoryGroup, Long> {

    @Query("SELECT cg " +
            " FROM CategoryGroup cg " +
            "ORDER BY cg.id")
    List<CategoryGroup> findCategoryGroups();

    @Query("SELECT cg " +
            " FROM CategoryGroup cg " +
            "WHERE cg.id =:categoryGroupId")
    CategoryGroup findCategoryGroup(@Param("categoryGroupId") long categoryGroupId);
}

package com.flight.overall.repository;

import com.flight.overall.entity.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("SELECT c " +
            " FROM Category c " +
            "WHERE c.categoryGroup IS NOT NULL " +
            "ORDER BY c.id")
    List<Category> findActiveCategories();

    @Query("SELECT c " +
            " FROM Category c " +
            "WHERE c.id =:id")
    Category findCategory(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("UPDATE Category c" +
            " SET c.categoryGroup = null " +
            "WHERE c.categoryGroup.id = :groupId")
    void deleteCategoryGroup(@Param("groupId") long groupId);
}

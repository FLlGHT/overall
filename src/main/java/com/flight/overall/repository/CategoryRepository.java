package com.flight.overall.repository;

import com.flight.overall.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    @Query("SELECT c " +
            " FROM Category c " +
            "ORDER BY c.id")
    List<Category> findCategories();
}

package com.flight.overall.service;

import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.entity.Category;
import com.flight.overall.mapper.EntityMapper;
import com.flight.overall.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EntityMapper mapper;

    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public List<CategoryDTO> getCategories() {
        Iterable<Category> categories = findAllCategories();
        return mapper.toCategories(categories);
    }

}

package com.flight.overall.service;


import com.flight.overall.dto.CategoriesDTO;
import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Category;
import com.flight.overall.entity.Role;
import com.flight.overall.mapper.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityMapper mapper;

    public String saveCategories(Account account, Model model, CategoriesDTO categories) {

        return "admin/categories";
    }
}

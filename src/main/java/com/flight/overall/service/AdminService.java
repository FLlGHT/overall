package com.flight.overall.service;


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

    public String getAdminPage(Account account, Model model) {
        if (account.getRole().equals(Role.ADMIN)) {
            return "admin";
        }
        else {
            model.addAttribute("message", "You do not have rights to this page");
            return "error";
        }
    }

    public String getCategoriesPage(Account account, Model model) {
        if (account.getRole().equals(Role.ADMIN)) {
            List<CategoryDTO> categories = categoryService.getCategories();
            model.addAttribute("categories", categories);
            return "admin-categories";
        }
        else {
            model.addAttribute("message", "You do not have rights to this page");
            return "error";
        }
    }
}

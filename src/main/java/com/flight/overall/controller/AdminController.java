package com.flight.overall.controller;


import com.flight.overall.dto.CategoriesDTO;
import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.dto.CategoryGroupDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Role;
import com.flight.overall.service.AdminService;
import com.flight.overall.service.CategoryService;
import com.flight.overall.utils.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/start")
    public String getAdminPage(@AuthenticationPrincipal Account account,
                               Model model) {
        if (account.getRole().equals(Role.ADMIN))
            return "admin/admin";

        return ErrorHandler.handleAccessViolation(model);
    }

    @GetMapping("/categories")
    public String getCategoriesPage(@AuthenticationPrincipal Account account,
                                    Model model) {
        if (account != null && account.getRole().equals(Role.ADMIN)) {
            CategoriesDTO categories = categoryService.getCategories();
            List<CategoryGroupDTO> groups = categoryService.getCategoryGroups();

            model.addAttribute("categories", categories);
            model.addAttribute("groups", groups);
            model.addAttribute("newCategory", new CategoryDTO());

            return "admin/admin-categories";
        }

        return ErrorHandler.handleAccessViolation(model);
    }

    @PostMapping("/categories/save")
    public String saveCategories(@AuthenticationPrincipal Account account,
                                 @ModelAttribute CategoriesDTO categories,
                                 Model model) {

        categoryService.saveCategories(categories);

        model.addAttribute("message", "Categories saved successfully");
        return getCategoriesPage(account, model);
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute CategoryDTO category,
                              @AuthenticationPrincipal Account account,
                              Model model) {

        categoryService.createCategory(category);

        model.addAttribute("message", "Category added successfully");
        return getCategoriesPage(account, model);
    }
}

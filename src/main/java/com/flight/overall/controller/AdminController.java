package com.flight.overall.controller;


import com.flight.overall.dto.CategoriesDTO;
import com.flight.overall.dto.CategoryDTO;
import com.flight.overall.dto.CategoryGroupDTO;
import com.flight.overall.dto.CategoryGroupsDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Role;
import com.flight.overall.service.AdminService;
import com.flight.overall.service.CategoryService;
import com.flight.overall.utils.ErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            model.addAttribute("emptyGroup", new CategoryGroupDTO());
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

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal Account account,
                                 Model model) {

        categoryService.deleteCategory(id);
        model.addAttribute("message", "Category delete successfully");
        return getCategoriesPage(account, model);
    }

    @GetMapping("/category-groups")
    public String getCategoryGroups(@AuthenticationPrincipal Account account,
                                    Model model) {
        if (account != null && account.getRole().equals(Role.ADMIN)) {
            CategoryGroupsDTO categoryGroups = new CategoryGroupsDTO(categoryService.getCategoryGroups());

            model.addAttribute("categoryGroups", categoryGroups);
            model.addAttribute("newCategoryGroup", new CategoryGroupDTO());

            return "admin/admin-category-groups";
        }

        return ErrorHandler.handleAccessViolation(model);
    }

    @PostMapping("/category-groups/save")
    public String saveCategoryGroups(@AuthenticationPrincipal Account account,
                                     @ModelAttribute CategoryGroupsDTO categoryGroups,
                                     Model model) {

        categoryService.saveCategoryGroups(categoryGroups);

        model.addAttribute("message", "Category groups saved successfully");
        return getCategoryGroups(account, model);
    }

    @PostMapping("/category-groups/add")
    public String addCategoryGroup(@ModelAttribute CategoryGroupDTO categoryGroup,
                              @AuthenticationPrincipal Account account,
                              Model model) {

        categoryService.createCategoryGroup(categoryGroup);

        model.addAttribute("message", "Category group added successfully");
        return getCategoryGroups(account, model);
    }

    @PostMapping("/category-groups/delete/{id}")
    public String deleteCategoryGroup(@PathVariable("id") Long id,
                                      @AuthenticationPrincipal Account account,
                                      Model model) {

        categoryService.deleteCategoryGroup(id);
        model.addAttribute("message", "Category group delete successfully");
        return getCategoriesPage(account, model);
    }
}

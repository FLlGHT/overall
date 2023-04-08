package com.flight.overall.controller;


import com.flight.overall.dto.CategoriesDTO;
import com.flight.overall.entity.Account;
import com.flight.overall.entity.Role;
import com.flight.overall.service.AdminService;
import com.flight.overall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminRestController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public ResponseEntity<CategoriesDTO> getCategoriesPage(Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();

        if (account != null && account.getRole().equals(Role.ADMIN)) {
            CategoriesDTO categories = categoryService.getCategories();
            return ResponseEntity.ok(categories);
        }

        return ResponseEntity.badRequest().build();
    }

    /*
    @PostMapping("/categories/save")
    public String saveCategories(Authentication authentication,
                                 @ModelAttribute CategoriesDTO categories,
                                 Model model) {
        Account account = (Account) authentication.getPrincipal();
        categoryService.saveCategories(categories);

        model.addAttribute("message", "Categories saved successfully");
        return getCategoriesPage(account);
    }

    @PostMapping("/categories/add")
    public String addCategory(@ModelAttribute CategoryDTO category,
                              Authentication authentication,
                              Model model) {
        Account account = (Account) authentication.getPrincipal();
        categoryService.createCategory(category);

        model.addAttribute("message", "Category added successfully");
        return getCategoriesPage(account);
    }

    @PostMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id,
                                 Authentication authentication,
                                 Model model) {
        Account account = (Account) authentication.getPrincipal();
        categoryService.deleteCategory(id);
        model.addAttribute("message", "Category delete successfully");
        return getCategoriesPage(account);
    }

    @GetMapping("/category-groups")
    public String getCategoryGroups(Authentication authentication,
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
    public String saveCategoryGroups(Authentication authentication,
                                     @ModelAttribute CategoryGroupsDTO categoryGroups,
                                     Model model) {

        categoryService.saveCategoryGroups(categoryGroups);

        model.addAttribute("message", "Category groups saved successfully");
        return getCategoryGroups(account, model);
    }

    @PostMapping("/category-groups/add")
    public String addCategoryGroup(@ModelAttribute CategoryGroupDTO categoryGroup,
                                   Authentication authentication,
                                   Model model) {

        categoryService.createCategoryGroup(categoryGroup);

        model.addAttribute("message", "Category group added successfully");
        return getCategoryGroups(account, model);
    }

    @PostMapping("/category-groups/delete/{id}")
    public String deleteCategoryGroup(@PathVariable("id") Long id,
                                      Authentication authentication,
                                      Model model) {

        categoryService.deleteCategoryGroup(id);
        model.addAttribute("message", "Category group delete successfully");
        return getCategoriesPage(account, model);
    }


     */
}

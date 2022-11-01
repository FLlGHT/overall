package com.flight.overall.controller;


import com.flight.overall.entity.Account;
import com.flight.overall.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/start")
    public String getAdminPage(@AuthenticationPrincipal Account account,
                               Model model) {
        return adminService.getAdminPage(account, model);
    }

    @GetMapping("/categories")
    public String getCategoriesPage(@AuthenticationPrincipal Account account,
                                    Model model) {
        return adminService.getCategoriesPage(account, model);
    }
}

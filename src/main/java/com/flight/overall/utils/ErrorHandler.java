package com.flight.overall.utils;

import org.springframework.ui.Model;

public class ErrorHandler {

    public static String handleError(Model model, String message) {
        model.addAttribute("message", message);
        return "errors/error";
    }

    public static String handleAccessViolation(Model model) {
        return handleError(model, "You do not have rights to this page");
    }

    public static String handleUserAbsence(Model model) {
        return handleError(model, "There is no user with this username");
    }

}

package org.quiz.app.controllers;

import org.quiz.app.exceptions.InvalidDataException;
import org.quiz.app.models.UserDto;
import org.quiz.app.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService = new UserService();

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(UserDto userDto, Model model) {
        System.out.println(userDto);
        try {
            userService.register(userDto);
        } catch (InvalidDataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
        return "profile";
    }
}

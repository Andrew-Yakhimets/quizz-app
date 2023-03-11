package org.quiz.app.controllers;

import org.quiz.app.entities.User;
import org.quiz.app.exceptions.InvalidDataException;
import org.quiz.app.models.UserDto;
import org.quiz.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String LoginUser(String email, String password, Model model) {
        System.out.println(email);
        System.out.println(password);
        Optional<User> optionalUser = userService.findUserOptionalInUserRepo(email, password);

        if(optionalUser.isPresent()) {
            model.addAttribute("user" , optionalUser.get());
            return "profile";
        } else {
            model.addAttribute("errorMessage", "Invalid Email or Password");
            return "login";
        }
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(UserDto user, Model model) {
        System.out.println(user);
        model.addAttribute("user", user);
        try {
            userService.register(user);
        } catch (InvalidDataException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "registration";
        }
        return "profile";
    }

    @PostMapping("/profile")
    public String changeUserName(String userName) {
        return "/profile";
    }
}

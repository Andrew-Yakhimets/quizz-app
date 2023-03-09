package org.quiz.app.controllers;

import org.quiz.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/")
    public String registration() {
        return "registration";
    }

    @PostMapping("/")
    public String saveUser(User user, Model model) {
        model.addAttribute("user", user.getName());
        System.out.println(user); // todo save to DB\

        return "profile";
    }
}

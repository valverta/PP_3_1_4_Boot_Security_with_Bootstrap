package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserCRUDService;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserCRUDService userCRUDService;

    public UserController(UserCRUDService userCRUDService) {
        this.userCRUDService = userCRUDService;
    }

    @GetMapping("/")
    public String userPage(Model model, Principal principal) {
        Optional<User> user = userCRUDService.findByUsername(principal.getName());
        model.addAttribute("user", user.get());
        return "user";
    }

//    @GetMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userCRUDService.getUserById(id));
//        return "update";
//    }
//
//    @PatchMapping("/save-user")
//    public String saveUser(@ModelAttribute("user") User user) {
//        userCRUDService.saveOrUpdate(user);
//        return "user";
//    }
}

package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleService roleService;

    @Autowired
    public RegistrationController(UserService userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String registration(Model model, Principal principal) {
        Optional<User> user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user.get());
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(Model model, @Valid User user, BindingResult bindingResult, Principal principal) {
        Optional<User> user1 = userService.findByUsername(principal.getName());
        model.addAttribute("user", user1.get());
        model.addAttribute("newUser", user);
        model.addAttribute("allRoles", roleService.getAllRoles());

        BindingResult bi = bindingResult;
        userValidator.validate(user, bindingResult);

        if (bi.hasErrors() || bindingResult.hasErrors()) {
            return "/registration";
        }
        userService.save(user);
        return "redirect:/admin/";
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("newUser", new User());
//        model.addAttribute("allRoles", roleService.getAllRoles());
//        return "registration";
//    }
//    @PostMapping("/registration")
//    public String performRegistration(Model model, @Valid User user, BindingResult bindingResult) {
//        model.addAttribute("newUser", user);
//        model.addAttribute("allRoles", roleService.getAllRoles());
//
//        BindingResult bi = bindingResult;
//        userValidator.validate(user, bindingResult);
//
//        if (bi.hasErrors() || bindingResult.hasErrors()) {
//            return "/registration";
//        }
//        userService.save(user);
//        return "redirect:/admin/";
//    }
}

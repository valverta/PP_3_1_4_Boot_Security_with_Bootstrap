package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserCRUDService;
import ru.kata.spring.boot_security.demo.utils.UserValidator;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserCRUDService userCRUD;
    private final UserValidator userValidator;

    @Autowired
    public RegistrationController(UserCRUDService userCRUD, UserValidator userValidator) {
        this.userCRUD = userCRUD;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        BindingResult bi = bindingResult;
        userValidator.validate(user, bindingResult);

        if (bi.hasErrors() || bindingResult.hasErrors())
            return "registration";

        userCRUD.save(user);
        return "redirect:/login";
    }
}

package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserCRUDService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserCRUDService userCRUDService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserCRUDService userCRUDService, RoleService roleService) {
        this.userCRUDService = userCRUDService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String adminPage(Model model) {
        model.addAttribute("allUsers", userCRUDService.getAllUsers());
        return "admin/admin";
    }

    @GetMapping("/update/{id}")
    public String updateRole(@PathVariable("id") long id, Model model) {
        //model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("user", userCRUDService.getUserById(id));
        return "admin/update";
    }

    @PatchMapping("/save-user")
    public String saveRole(@ModelAttribute("user") User user) {
        userCRUDService.saveOrUpdate(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userCRUDService.removeById(id);
        return "redirect:/admin/";
    }
}

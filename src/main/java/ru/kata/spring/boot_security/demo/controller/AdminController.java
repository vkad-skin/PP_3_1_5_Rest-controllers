package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "all-users";
    }

    @GetMapping("/new-user")
    public String addNewUser(ModelMap model) {
        User user = new User();
        List<Role> roles =  roleRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "info-user";
    }

    @PostMapping("/add-or-update-user")
    public String saveOrUpdateUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/get-user")
    public String getUserById(@RequestParam("userId") int id, ModelMap model) {
        User user = userService.getById(id);
        List<Role> roles =  roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return "info-user";
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}

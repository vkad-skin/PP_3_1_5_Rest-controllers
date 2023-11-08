package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImpl userServiceImpl;

    private final RoleRepository roleRepository;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleRepository roleRepository) {
        this.userServiceImpl = userServiceImpl;
        this.roleRepository = roleRepository;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userServiceImpl.getAllUsers());
        return "all-users";
    }

    @GetMapping("/new-user")
    public String addNewUser(ModelMap model) {
        User user = new User();
        List<Role> roles = roleRepository.findAll();

        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "add-user";
    }

    @PostMapping("/add-or-update-user")
    public String saveOrUpdateUser(@ModelAttribute User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute User user) {
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/get-user")
    public String getUserById(@RequestParam("userId") int id, ModelMap model) {
        User user = userServiceImpl.getById(id);
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);

        return "update-user";
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam("userId") int id) {
        userServiceImpl.deleteUserById(id);
        return "redirect:/admin";
    }
}

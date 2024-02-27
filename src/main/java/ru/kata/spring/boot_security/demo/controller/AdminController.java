package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.impl.UserServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userServiceImpl;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(ModelMap model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        model.addAttribute("users", userServiceImpl.getAllUsers());
        model.addAttribute("authorities", userServiceImpl.findByEmail(auth.getName()));
        return "admin";
    }

    @PostMapping("/add-user")
    public String saveUser(@ModelAttribute User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        User oldUser = userServiceImpl.getById(user.getId());

        if (user.getRoles().isEmpty()) {
            user.setRoles(oldUser.getRoles());
        }
        userServiceImpl.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        if (!(userServiceImpl.getById(id).getEmail())
                .equals(SecurityContextHolder.getContext().getAuthentication().getName())) {
            userServiceImpl.deleteUserById(id);
        }
        return "redirect:/admin";
    }
}

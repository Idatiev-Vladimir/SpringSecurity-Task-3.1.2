package ru.itmentor.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.UserService;


@Controller
@RequestMapping("/admin")
public class TableOfUsersControllers {

    private final UserService userService;

    @Autowired
    public TableOfUsersControllers(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("Users", userService.getAllUsers());
        return "admin/tableOfUsers";
    }

    @GetMapping("/new")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/newUser";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user.getId(), user);
        return "redirect:/admin";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@PathVariable("id") long id,
                           Model model) {
        model.addAttribute("user", userService.showUser(id));
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/editUser";
    }

    @DeleteMapping("/delete/{id}")
    public String removeUser(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/admin";
    }
}

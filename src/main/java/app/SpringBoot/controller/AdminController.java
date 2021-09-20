package app.SpringBoot.controller;

import app.SpringBoot.model.User;
import app.SpringBoot.service.RoleService;
import app.SpringBoot.service.UserService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.AssertionFailure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
private String oldPassword=null;
    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping({"", "list"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user-list";
    }

    @GetMapping(value = "/new")
    public String addUserForm(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "user-form-add";
    }

    @GetMapping("/{id}/edit")
    public String editUserForm(@PathVariable(value = "id", required = true) Long userId, Model model) {
        try {
            oldPassword = userService.find(userId).getPassword();
            model.addAttribute("user", userService.find(userId));
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return "redirect:/admin";
        }
        model.addAttribute("allRoles", roleService.findAllRoles());
        return "user-form";
    }

    @PostMapping("/add")
    public String saveUser(@Valid @ModelAttribute("user") User user) {
        try {
            return userService.save(user) ? "redirect:/admin" : "user-form";
        } catch (AssertionFailure | UnexpectedRollbackException e) {
            return "user-form";
        }
    }

    @PutMapping()
    public String updateUser(@Valid @ModelAttribute("user") User user) {
        try {
            if (StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(oldPassword);
            }
            return userService.update(user) ? "redirect:/admin" : "user-form";
        } catch (AssertionFailure | UnexpectedRollbackException e) {
            return "user-form";
        }
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long userId) {
        userService.delete(userId);

        return "redirect:/admin";
    }
}

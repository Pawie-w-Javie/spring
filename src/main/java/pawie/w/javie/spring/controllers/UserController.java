package pawie.w.javie.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.services.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "userForm";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserEntity user) {
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "userForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
package pawie.w.javie.spring.controllers;

import org.springframework.web.bind.annotation.*;
import pawie.w.javie.spring.models.UserEntity;
import pawie.w.javie.spring.services.UserService;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return userService.findAll();
    }


    @GetMapping("/get/{id}")
    public UserEntity getOne(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/add")
    public void create(@RequestBody UserEntity user) {
        userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
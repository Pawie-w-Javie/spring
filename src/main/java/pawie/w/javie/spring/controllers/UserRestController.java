package pawie.w.javie.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Zwraca 200
    @GetMapping
    public ResponseEntity<List<UserEntity>> getAll() {
        List<UserEntity> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Zwraca 200 lub 404
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getOne(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    // Zwraca 201
    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody UserEntity user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    // Zwraca 204
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
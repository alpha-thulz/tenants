package za.co.tyaphile.tenants.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dao.LoginDao;
import za.co.tyaphile.tenants.dao.UserDao;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Used to get a list of all users")
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Operation(summary = "Used to get a user by ID")
    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(userService.getUser(id));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @Operation(summary = "Register a new user", security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody UserDao user) {
        return ResponseEntity.status(201).body(userService.createUser(user));
    }

    @Operation(summary = "Login with an existing user", security = {@SecurityRequirement(name = "bearer-key")})
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody LoginDao user) {
        return ResponseEntity.ok(userService.login(user));
    }

    @Operation(summary = "Used to update a user matching ID")
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDao user) {
        return ResponseEntity.ok(userService.updateUser(id, user));
    }

    @Operation(summary = "Used to delete a user matching an ID")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
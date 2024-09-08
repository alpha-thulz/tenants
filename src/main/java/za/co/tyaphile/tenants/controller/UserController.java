package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dto.UserDto;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        if (users.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(users);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable("userId") String user_id) {
        User user = userService.getUserById(user_id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String user_id, @RequestBody @Valid UserDto user) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.updateUser(user_id, user));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") String user_id) {
        userService.deleteUser(user_id);
    }
}
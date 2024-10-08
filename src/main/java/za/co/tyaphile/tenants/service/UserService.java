package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dto.UserDto;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.repo.UserRepo;

import java.util.Base64;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(String id) {
        return userRepo.findById(id).orElseThrow(() -> new IndexOutOfBoundsException("User with id '" + id + "' not found"));
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User createUser(UserDto user) {
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        User newUser = new User(user.getUsername(), password, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
        return userRepo.save(newUser);
    }

    public User updateUser(String id, UserDto user) {
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes());

        User updateUser = getUserById(id);
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(password);
        updateUser.setFirstName(user.getFirstName());
        updateUser.setLastName(user.getLastName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());

        return userRepo.save(updateUser);
    }

    public void deleteUser(String id) {
        getUserById(id);
        userRepo.deleteById(id);
    }
}
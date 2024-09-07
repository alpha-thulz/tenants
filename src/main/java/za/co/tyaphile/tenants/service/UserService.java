package za.co.tyaphile.tenants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dao.UserDao;
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

    public User createUser(UserDao user) {
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        User newUser = new User(user.getUsername(), password, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
        return userRepo.save(newUser);
    }

    public User updateUser(String id, UserDao user) {
        getUserById(id);
        String password = Base64.getEncoder().encodeToString(user.getPassword().getBytes());
        User updatedDetails = new User(id, user.getUsername(), password, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
        return userRepo.save(updatedDetails);
    }

    public void deleteUser(String id) {
        getUserById(id);
        userRepo.deleteById(id);
    }
}
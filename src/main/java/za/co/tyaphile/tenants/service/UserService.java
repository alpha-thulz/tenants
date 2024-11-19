package za.co.tyaphile.tenants.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.tyaphile.tenants.dao.UserDao;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.repo.UserRepo;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService tokenService;

    public List<User> getUsers() {
        return repo.findAll();
    }

    public User getUser(String id) {
        return repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User ID " + id + " not found"));
    }

    public Map<String, Object> login(UserDao dao) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dao.getUsername(), dao.getPassword()));
        if (authentication.isAuthenticated()) {
            return tokenService.generateToken(dao.getUsername());
        }
        return null;
    }

    public Map<String, Object> createUser(UserDao dao) {
        if (repo.findByUsername(dao.getUsername()).isPresent())
            throw new RuntimeException("Username already exists");

        User user = User.builder()
                .firstname(dao.getFirstname())
                .lastname(dao.getLastname())
                .username(dao.getUsername())
                .password(encoder.encode(dao.getPassword()))
                .email(dao.getEmail())
                .enabled(dao.isEnabled())
                .role(dao.getRole())
                .build();

        repo.save(user);
        return tokenService.generateToken(user.getUsername());
    }

    public User updateUser(String id, UserDao dao) {
        User user = getUser(id);
        user.setFirstname(dao.getFirstname());
        user.setLastname(dao.getFirstname());
        user.setUsername(dao.getUsername());
        user.setPassword(encoder.encode(dao.getPassword()));
        user.setEmail(dao.getEmail());
        user.setEnabled(dao.isEnabled());
        user.setRole(dao.getRole());

        return repo.save(user);
    }

    public void deleteUser(String id) {
        User user = getUser(id);
        repo.delete(user);
    }
}
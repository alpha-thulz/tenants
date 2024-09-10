package za.co.tyaphile.tenants.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import za.co.tyaphile.tenants.dto.UserDto;
import za.co.tyaphile.tenants.model.User;
import za.co.tyaphile.tenants.service.WebService;

@Controller
public class WebController {

    private final WebService webService;

    @Autowired
    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping({"/", "/home"})
    public String home(Model model) {
        model.addAttribute("users", webService.getUsers());
        return "index";
    }

    @GetMapping("/new_user")
    public String addUser(Model model) {
        return "./user/new_user";
    }

    @PostMapping("/new_user")
    public String addUser(@ModelAttribute("user") @Valid UserDto user) {
        webService.createUser(user);
        return "redirect:/home";
    }

    @GetMapping("/user_details/{id}")
    public String getUserDetails(@PathVariable String id, Model model) {
        model.addAttribute("user", webService.getUserById(id));
        return "./user/user_details";
    }

    @PostMapping("/user_details/{id}")
    public String deleteUser(@PathVariable String id) {
        webService.deleteUser(id);
        return "redirect:/home";
    }

    @GetMapping("/update_user/{id}")
    public String updateUser(@PathVariable String id, Model model) {
        model.addAttribute("user", webService.getUserById(id));
        return "./user/update_user";
    }

    @PostMapping("/update_user/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("user") @Valid UserDto user) {
        webService.updateUser(id, user);
        return "redirect:/user_details/" + id;
    }
}

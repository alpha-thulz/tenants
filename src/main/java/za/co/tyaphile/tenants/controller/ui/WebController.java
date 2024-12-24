package za.co.tyaphile.tenants.controller.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import za.co.tyaphile.tenants.service.UserService;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService _userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("users", _userService.getUsers());
        return "index";
    }

//    @GetMapping("/building/{id}")
//    public String building(Model model, @PathVariable("id") String id) {
//
//    }
}

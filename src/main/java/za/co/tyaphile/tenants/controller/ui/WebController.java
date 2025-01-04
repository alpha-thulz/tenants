package za.co.tyaphile.tenants.controller.ui;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.tyaphile.tenants.dao.LoginDao;
import za.co.tyaphile.tenants.service.UserService;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final UserService _userService;

    @GetMapping({"/", ""})
    public String index(Model model) {
        model.addAttribute("users", _userService.getUsers());
        return "index";
    }

    @GetMapping("/building/{id}")
    public String building(Model model, @PathVariable("id") String id, HttpServletRequest request) {
        return "user/building";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new LoginDao());
        return "login";
    }

    @PostMapping("/validate")
    public String loginSubmit(@ModelAttribute("user") LoginDao user, HttpServletResponse response) {
        Map<String, Object> creds = _userService.login(user);
        if (creds.containsKey("accessToken") && creds.containsKey("user_id")) {
            response.addHeader("Authorization", "Bearer ".concat(creds.get("accessToken").toString()));
            return "redirect:/?success=true";
        }
        return "redirect:/login?error";
    }
}
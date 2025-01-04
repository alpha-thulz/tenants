package za.co.tyaphile.tenants.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
public class WebViewController {

    @GetMapping({"/", ""})
    public String index() {
        return "index";
    }
}
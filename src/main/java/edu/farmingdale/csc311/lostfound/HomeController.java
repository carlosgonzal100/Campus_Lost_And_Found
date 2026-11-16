package edu.farmingdale.csc311.lostfound;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // The Lost & Found page, now a Thymeleaf template in templates/index.html.
    @GetMapping("/")
    public String home() {
        return "index";
    }
}

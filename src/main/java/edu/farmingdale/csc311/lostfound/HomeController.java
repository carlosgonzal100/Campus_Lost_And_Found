package edu.farmingdale.csc311.lostfound;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    // The Lost & Found page, now a Thymeleaf template in templates/index.html.
    @GetMapping("/")
    public String home(Principal principal, Model model) {
        // The signed-in user, for "Signed in as" on the page.
        model.addAttribute("username", principal.getName());
        return "index";
    }
}

package edu.farmingdale.csc311.lostfound.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(AppUserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam(defaultValue = "") String username,
                           @RequestParam(defaultValue = "") String password,
                           Model model) {
        String error = null;
        if (username.length() < 3) {
            error = "Username must be at least 3 characters.";
        } else if (password.length() < 6) {
            error = "Password must be at least 6 characters.";
        } else if (repository.existsByUsername(username)) {
            error = "That username is already taken.";
        }

        if (error != null) {
            model.addAttribute("error", error);
            return "register";
        }

        // Save the password as a BCrypt hash, never as plain text.
        repository.save(new AppUser(username, passwordEncoder.encode(password)));
        return "redirect:/login?registered";
    }
}

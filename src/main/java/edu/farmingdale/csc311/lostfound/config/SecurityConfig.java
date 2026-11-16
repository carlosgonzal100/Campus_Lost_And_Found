package edu.farmingdale.csc311.lostfound.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Passwords are stored as BCrypt hashes, never as plain text.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Anyone may open these pages. Every other page requires sign-in.
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register", "/login", "/error", "/h2-console/**").permitAll()
                        .anyRequest().authenticated())
                // Our own sign-in page at /login. After signing in, go to /.
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true))
                // After signing out, go to /login?logout.
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout"))
                // The H2 console posts its own forms and runs inside frames.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()));

        return http.build();
    }
}

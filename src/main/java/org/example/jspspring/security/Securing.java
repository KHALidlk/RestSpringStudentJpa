package org.example.jspspring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Securing {

    private static final Logger logger = LoggerFactory.getLogger(Securing.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        logger.info("Creating user details service");

        PasswordEncoder encoder = passwordEncoder();

        // Regular user
        UserDetails regularUser = User.builder()
                .username("user")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();
        logger.info("Created user: {}", regularUser.getUsername());

        // Admin
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("ADMIN")
                .build();
        logger.info("Created admin: {}", admin.getUsername());

        // Super admin (multi-roles)
        UserDetails superAdmin = User.builder()
                .username("khalid")
                .password(encoder.encode("khalid123"))
                .roles("USER", "ADMIN")
                .build();
        logger.info("Created super admin: {}", superAdmin.getUsername());

        return new InMemoryUserDetailsManager(regularUser, admin, superAdmin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authz -> authz
                        // Explicitly use AntPathRequestMatcher for each path
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/delete/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/update/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/name/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/email/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/id/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/JspSpring/students/profile")).hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {});

        logger.info("Security filter chain built successfully");
        return http.build();
    }
}
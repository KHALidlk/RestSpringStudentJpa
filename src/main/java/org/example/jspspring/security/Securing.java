package org.example.jspspring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class Securing {
    private static final Logger logger = LoggerFactory.getLogger(Securing.class);

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        logger.info("Configuring security filter chain");
        
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
        
        http
                .authorizeHttpRequests((authz) -> {
                    try {
                        authz
                            // Admin role endpoints
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/add")).hasRole("ADMIN")
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/delete/**")).hasRole("ADMIN")
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/update/**")).hasRole("ADMIN")
                            
                            // Manager role endpoints
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/name/**")).hasAnyRole("ADMIN")
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/email/**")).hasAnyRole("ADMIN")
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/id/**")).hasAnyRole("ADMIN")
                            
                            // User role endpoints
                            .requestMatchers(mvcMatcherBuilder.pattern("/students/profile")).hasAnyRole("ADMIN", "USER")
                            
                            // IMPORTANT: This denies all other requests
                            .anyRequest().authenticated();
                        logger.info("Security rules configured successfully");
                    } catch (Exception e) {
                        logger.error("Error configuring security rules", e);
                        throw e;
                    }
                })
                .httpBasic(basic -> {
                    logger.info("HTTP Basic authentication enabled");
                })
                .csrf(csrf -> {
                    csrf.disable();
                    logger.info("CSRF protection disabled");
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    logger.info("Session management set to STATELESS");
                });

        SecurityFilterChain chain = http.build();
        logger.info("Security filter chain built successfully");
        return chain;
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        logger.info("Creating user details service");
        
        // Regular user with USER role
        UserDetails regularUser = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123"))
                .roles("USER")
                .build();
        logger.info("Created user: {}", regularUser.getUsername());

        // Admin with ADMIN role
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build();
        logger.info("Created admin: {}", admin.getUsername());

        // Manager with MANAGER role
        UserDetails manager = User.builder()
                .username("manager")
                .password(passwordEncoder.encode("manager"))
                .roles("MANAGER")
                .build();
        logger.info("Created manager: {}", manager.getUsername());

        // Super admin with multiple roles
        UserDetails superAdmin = User.builder()
                .username("khalid")
                .password(passwordEncoder.encode("khalid123"))
                .roles("ADMIN", "USER")
                .build();
        logger.info("Created super admin: {}", superAdmin.getUsername());

        return new InMemoryUserDetailsManager(regularUser, admin, manager, superAdmin);
    }

    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}

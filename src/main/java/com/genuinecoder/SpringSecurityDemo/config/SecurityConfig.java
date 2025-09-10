package com.genuinecoder.SpringSecurityDemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(registry -> {
                    registry.requestMatchers("/home", "/login", "/error").permitAll()
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").hasRole("USER")
                            .anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginProcessingUrl("/authenticate")  // URL to submit login form
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")                   // URL to trigger logout
                        .logoutSuccessUrl("/home?logout=true")  // Redirect after logout
                        .invalidateHttpSession(true)            // Invalidate session
                        .deleteCookies("JSESSIONID")           // Delete cookies
                        .permitAll()
                )
                .build();
    }

    /*
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails admin = User.builder()
                .username("Phenny")
                .password(passwordEncoder().encode("4321"))
                .roles("ADMIN", "USER")
                .build();

        UserDetails user = User.builder()
                .username("Mwaisaka")
                .password(passwordEncoder().encode("1234"))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
     */

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsService();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
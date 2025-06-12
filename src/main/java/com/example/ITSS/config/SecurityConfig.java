package com.example.ITSS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    String[] publicUrls = {"/css/**", "/js/**", "/images/**", "/error", };
    String[] adminUrls = {"/api/admin/**", "api/users/addUser", "api/users/deleteUser", "api/users/updateUser"};
    String[] teacherUrls = {"/api/**"};
    String[] studentUrls = {"/api/**"};


    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails accountant1 = User.withUsername("accountant1")
                .password(passwordEncoder().encode("accountant1pass"))
                .roles("TEACHER")
                .build();
        UserDetails leader1 = User.withUsername("student1")
                .password(passwordEncoder().encode("leader1pass"))
                .roles("STUDENT")
                .build();
        UserDetails leader2 = User.withUsername("admin1")
                .password(passwordEncoder().encode("admin1pass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(accountant1, leader1, leader2);
    }

    //Cần sửa lại phần filterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                            .requestMatchers(publicUrls).permitAll()
//                            .requestMatchers(adminUrls).hasRole("ADMIN")
//                            .requestMatchers("/api/**").hasAnyRole("STUDENT", "TEACHER")
                            .anyRequest().authenticated()
                )
                .formLogin(login->
                        login.loginPage("/login")
                                .loginProcessingUrl("/process-login")
                                .successHandler(customAuthenticationSuccessHandler())
                                .failureUrl("/login?error=true")
                                .permitAll()// form xác nhận
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/login") // URL chuyển hướng sau khi đăng xuất thành công
                        .permitAll()
                );
        return http.build();
        // ...
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return new OurUserDetailService();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}

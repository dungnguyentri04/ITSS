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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails accountant1 = User.withUsername("accountant1")
                .password(passwordEncoder().encode("accountant1pass"))
                .roles("ACCOUNTANT")
                .build();
        UserDetails leader1 = User.withUsername("leader1")
                .password(passwordEncoder().encode("leader1pass"))
                .roles("LEADER")
                .build();
        UserDetails leader2 = User.withUsername("leader2")
                .password(passwordEncoder().encode("leader2pass"))
                .roles("LEADER")
                .build();
        return new InMemoryUserDetailsManager(accountant1, leader1, leader2);
    }

    //Cần sửa lại phần filterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/register", "/css/**", "/js/**").permitAll()
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(login->
                        login.loginPage("/login")
                                .loginProcessingUrl("/process-login")
                                .defaultSuccessUrl("/index", true)
                                .permitAll()// form xác nhận
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL để đăng xuất
                        .logoutSuccessUrl("/index") // URL chuyển hướng sau khi đăng xuất thành công
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
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

//package org.mihaiaposotlproiectfinal;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    // roles admin allow to access /admin/**
//    // roles user allow to access /user/**
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/", "/home").permitAll()
//                .antMatchers("/clients/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/clients/user/**").hasAnyRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/clients/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    // create two users, admin and user
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}password").roles("ADMIN");
//    }
//}
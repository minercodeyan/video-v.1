package com.example.vidT.cfg;

import com.example.vidT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserService userService;

    @Autowired
    public WebSecurityConfig(UserService userService){
        this.userService=userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/all","/faq","/all/{id}","/reg","/activateUser","/profiles/{username}",
                        "/style/**").permitAll() //страницы с полным доступом
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error")
                .permitAll()
                .and()
                 .logout()
                 .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                 .invalidateHttpSession(true)
                 .clearAuthentication(true)
                 .deleteCookies("JSESSIONID")
                 .logoutSuccessUrl("/login?logout")
                 .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());//пароли не в явном виде

    }
}
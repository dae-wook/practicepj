package com.example.practicepj.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/"
                        , "/member/register"
                        , "/member/email-auth"
                        ,"/member/email-auth-complete"
                        ,"/js/**"
                        ,"/css/**")
                .permitAll();


        http.formLogin()
                .loginPage("/member/login")
                .failureHandler(null)
                .permitAll();

        super.configure(http);
    }
}

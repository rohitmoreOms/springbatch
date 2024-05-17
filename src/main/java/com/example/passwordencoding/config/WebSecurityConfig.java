package com.example.passwordencoding.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


/*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("user")
                .password(encoder().encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

 */
/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
                 http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/login", "/token").permitAll() // Exclude login and token endpoints from authentication
                .anyRequest()
                         .permitAll().and().build();
    }
    */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
               // .antMatchers("/logIn ").permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }



}

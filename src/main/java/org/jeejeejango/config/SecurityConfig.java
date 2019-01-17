package org.jeejeejango.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author jeejeejango
 * @since 19/11/2018 12:33 PM
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("user").password(encoder().encode("password1")).roles("USER").and()
            .withUser("admin").password(encoder().encode("password1")).roles("ADMIN");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .and()
            .authorizeRequests()
            .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
            .antMatchers("/management/health").permitAll()
            .antMatchers("/management/**").hasRole("ADMIN")
            .antMatchers("/swagger-ui.html").permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic()
            .and()
            .logout();
    }


}

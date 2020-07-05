package com.example.bicycleshop.security;

import com.example.bicycleshop.security.Roles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Getter
@Setter
@EnableWebSecurity
@NoArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
            .and()
            .headers().frameOptions().disable();

        http.authorizeRequests()
            .antMatchers("/start/**").hasAnyRole(Roles.EMPLOYEE.name(), Roles.MANAGER.name(), Roles.ADMINISTRATOR.name())
            .antMatchers("/managers/**").hasAnyRole(Roles.MANAGER.name(), Roles.ADMINISTRATOR.name())
            .antMatchers("/administrators/**").hasRole(Roles.ADMINISTRATOR.name())
            .and().csrf().disable()
            .formLogin()
            .and()
            .logout().permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/access-denied");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

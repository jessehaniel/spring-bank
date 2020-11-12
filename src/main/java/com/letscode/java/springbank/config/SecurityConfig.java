package com.letscode.java.springbank.config;

import com.letscode.java.springbank.security.LetsCodePasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("custom-security")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests(authorize -> {
                authorize
                    .antMatchers("/h2-console/**").permitAll()
                    .mvcMatchers("/users", "/logging").permitAll()
                    .mvcMatchers("/clientes/page").authenticated()
                    .mvcMatchers("/gerentes").hasRole("GERENTE_GERAL")
                    .mvcMatchers("/clientes").hasAnyRole("GERENTE_GERAL", "GERENTE", "CLIENTE")
                    .mvcMatchers("/produtos").hasAnyRole("GERENTE_GERAL", "GERENTE", "CLIENTE", "GUEST");
            })
            .authorizeRequests()
            .anyRequest().denyAll()
            .and()
            .formLogin().and()
            .httpBasic();
    
        //h2 console config
        http.headers().frameOptions().sameOrigin();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return LetsCodePasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
}

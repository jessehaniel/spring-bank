package com.letscode.java.springbank.config;

import com.letscode.java.springbank.security.LetsCodePasswordEncoderFactories;
import com.letscode.java.springbank.security.RestHeaderAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Profile("custom-security")
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
            UsernamePasswordAuthenticationFilter.class);
        
        http
            .csrf().disable()
            .authorizeRequests(authorize -> {
                authorize
                    .mvcMatchers("/users").permitAll();
//                  .antMatchers("/**").permitAll()
            })
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().and()
            .httpBasic();
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
        return LetsCodePasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("letscode")
            .password("{bcrypt}$2a$10$HMiL2rbcF1HjFSMi4mydAu4Uw2T1LxuemoJk9gu.jEWWWsV/oHram")//admin123
            .roles("ADMIN")
            .and()
            .withUser("professor")//password
            .password("{sha256}5dc411bd616087d9427282f95abe9093529299cb179302becd31d59bacc8ca9585c1054ce8cf959c")
            .roles("PROFESSOR")
            .and()
            .withUser("aluno")
            .password("{ldap}{SSHA}ub2AAm+7NnZk+b657zQUn59OTy4GZdU6CgkpbA==")//aluno
            .roles("ALUNO")
            .and()
            .withUser("guest")
            .password("{noop}guest123")
            .roles("GUEST");
    }
    
    //    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        final UserDetails admin = User.withDefaultPasswordEncoder()
//            .username("admin")
//            .password("admin123")
//            .roles("ADMIN")
//            .build();
//        final UserDetails guest = User.withDefaultPasswordEncoder()
//            .username("guest")
//            .password("guest123")
//            .roles("GUEST")
//            .build();
//
//        return new InMemoryUserDetailsManager(admin, guest);
//    }
}

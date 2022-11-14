package com.ra.filmservice.security;

import com.ra.filmservice.security.filters.AuthenticationJwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationJwtFilter jwtFilter;

    private final AuthEntryPoint authEntryPoint;

    public SecurityConfig(AuthenticationJwtFilter jwtFilter, AuthEntryPoint authEntryPoint) {
        this.jwtFilter = jwtFilter;
        this.authEntryPoint = authEntryPoint;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable();

        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/api/v1/films/addAll", "/api/v1/films/add", "/api/v1/films/delete",
                        "/api/v1/films/addSchedule", "/api/v1/films/update")
                .hasRole("ADMIN")

                .anyRequest().authenticated()

                .and()
                .sessionManagement()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)

                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }

}

package com.proyecto_titulacion.assettrack.config.security;

import com.proyecto_titulacion.assettrack.config.security.filter.JwtTokenValidator;
import com.proyecto_titulacion.assettrack.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtUtils jwtUtils;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(http -> {
                    // EndPoints Publicos
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // EndPoints Privados
                    http.requestMatchers(HttpMethod.GET, "/identity-document/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/permission/**").hasRole("ADMINISTRADOR");
                    http.requestMatchers(HttpMethod.PUT, "/permission/**").hasRole("ADMINISTRADOR");

                    http.requestMatchers(HttpMethod.GET, "/role/**").permitAll();
                    http.requestMatchers(HttpMethod.POST, "/role/**").hasRole("ADMINISTRADOR");
                    http.requestMatchers(HttpMethod.PUT, "/role/**").hasRole("ADMINISTRADOR");

                    http.requestMatchers(HttpMethod.GET, "/user/**").permitAll();
                    http.requestMatchers(HttpMethod.PATCH, "/user/**").hasRole("ADMINISTRADOR");
                    http.requestMatchers(HttpMethod.PUT, "/user/**").permitAll();

                    // EndPoints No Especificados
                    http.anyRequest().denyAll();
                })
                .addFilterBefore(new JwtTokenValidator(this.jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

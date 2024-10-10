package com.proyecto_titulacion.assettrack.config;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public BCrypt.Hasher passwordHasher() {
        return BCrypt.withDefaults();
    }

    @Bean
    public BCrypt.Verifyer passwordVerifier() {
        return BCrypt.verifyer();
    }
}

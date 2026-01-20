package com.store.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.annotation.PostConstruct;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    @Value("${user.empID:DEFAULT_USER}")
    private String empID;

    @PostConstruct
    public void printCheck() {
        System.err.println(empID);
    }

    @Bean
    public AuditorAware<String> auditorProvider() {

        return () -> Optional.of(empID);

    }
}
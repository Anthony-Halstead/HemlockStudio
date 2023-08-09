package com.HemlockStudiosWebsite.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.HemlockStudiosWebsite.repo")

@EntityScan(basePackages="com.HemlockStudiosWebsite.entity")
public class ApplicationConfig {
    
}

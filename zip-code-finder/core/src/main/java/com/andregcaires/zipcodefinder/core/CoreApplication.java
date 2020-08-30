package com.andregcaires.zipcodefinder.core;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.andregcaires.zipcodefinder.context.repositories",
		"com.andregcaires.zipcodefinder.core.services",
		"com.andregcaires.zipcodefinder.core.utils"})
@EntityScan(basePackages = {"com.andregcaires.zipcodefinder.domain.entities"})
@EnableJpaRepositories(basePackages = { "com.andregcaires.zipcodefinder.context.repositories" })
public class CoreApplication {

}

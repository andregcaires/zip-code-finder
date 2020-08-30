package com.andregcaires.zipcodefinder.context;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = { "com.andregcaires.zipcodefinder.domain.entities" })
@EnableJpaRepositories
public class ContextApplication {

}

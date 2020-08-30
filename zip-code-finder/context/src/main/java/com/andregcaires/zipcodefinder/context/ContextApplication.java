package com.andregcaires.zipcodefinder.context;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = { "com.andregcaires.zipcodefinder.domain.entities" })
public class ContextApplication {

}

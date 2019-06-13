package com.avandal.determinimize.service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"com.avandal.determinimize.persistence",
		"com.avandal.determinimize.control" 
})
public class SpringConfig {
	
}

package com.avandal.determinimize.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
		"com.avandal.determinimize.binding.parser",
		"com.avandal.determinimize.binding.mapper",
		"com.avandal.determinimize.persistence",
		"com.avandal.determinimize.service",
		"com.avandal.determinimize.control"
})
public class SpringConfig {
	
}
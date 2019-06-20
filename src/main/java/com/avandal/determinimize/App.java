package com.avandal.determinimize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;

import com.avandal.determinimize.config.SpringConfig;
import com.avandal.determinimize.control.ConsoleController;
import com.avandal.determinimize.service.exception.AutomatonException;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.avandal.determinimize.config",
		"com.avandal.determinimize.binding.mapper",
		"com.avandal.determinimize.persistence",
		"com.avandal.determinimize.service",
		"com.avandal.determinimize.control"
})
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
//		runConsole();
	}

	private static void runConsole() {
		GenericApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		ConsoleController control = context.getBean(ConsoleController.class);
		try {
			control.run();
		} catch (AutomatonException e) {
			e.printStackTrace();
		}
		context.close();
	}
}

package com.avandal.determinimize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.avandal.determinimize.control.ConsoleController;
import com.avandal.determinimize.service.config.SpringConfig;

public class App {
	public static void main(String[] args) {
		GenericApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		ConsoleController control = context.getBean(ConsoleController.class);
		control.run();
		context.close();
	}
}

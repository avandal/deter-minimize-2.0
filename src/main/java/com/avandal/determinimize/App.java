package com.avandal.determinimize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.avandal.determinimize.config.SpringConfig;
import com.avandal.determinimize.control.ConsoleController;
import com.avandal.determinimize.service.exception.AutomatonException;

public class App {
	public static void main(String[] args) {
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

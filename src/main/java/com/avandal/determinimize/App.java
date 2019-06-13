package com.avandal.determinimize;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.avandal.determinimize.model.State;
import com.avandal.determinimize.persistence.Automaton;
import com.avandal.determinimize.service.config.SpringConfig;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(SpringConfig.class);
		ctx.refresh();
		Automaton automaton = ctx.getBean(Automaton.class);
		ctx.close();
		
		automaton.addStates(
				new State("1", true, false),
				new State("2", false, false),
				new State("3", false, true));
		
		automaton.addTransition("1", "2", "a", "b", "c");
		automaton.addTransition("1", "3", "c", "d");
		automaton.addTransition("2", "1", "a");
		automaton.addTransition("1", "2", "c", "e");
		
		automaton.removeTransition("1", "2", "c", "d");
		
		System.out.println(automaton);
	}
}

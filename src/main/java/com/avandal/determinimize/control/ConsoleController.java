package com.avandal.determinimize.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avandal.determinimize.model.State;
import com.avandal.determinimize.persistence.Automaton;

@Controller
public class ConsoleController {
	@Autowired
	private Automaton automaton;
	
	private ConsoleController() {}
	
	public void run() {
		automaton.addStates(
				new State("1", true, false),
				new State("2", false, false),
				new State("3", false, true));
		
		automaton.addTransition("1", "2", "a", "b", "c");
		automaton.addTransition("1", "3", "c", "d");
		automaton.addTransition("2", "1", "a");
		automaton.addTransition("1", "2", "c", "e");
		
		automaton.removeTransition("1", "2", "a", "b", "c");
		
		System.out.println(automaton);
	}
}

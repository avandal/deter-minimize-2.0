package com.avandal.determinimize.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avandal.determinimize.model.State;
import com.avandal.determinimize.persistence.Automaton;
import com.avandal.determinimize.service.AutomatonService;

@Controller
public class ConsoleController {
	
	@Autowired
	private AutomatonService automatonService;
	
	private ConsoleController() {}
	
	public void run() {
		automatonService.mockAutomaton();
		automatonService.getStates().forEach(System.out::println);
		

//		System.out.println("Initial automaton:");
//		System.out.println(automaton);
//		
//		automaton.removeState("1");
//		
//		System.out.println("After removing 1");
//		System.out.println(automaton);
//		
//		automaton.addState(new State("1", false, false));
//		
//		System.out.println("After adding new 1");
//		System.out.println(automaton);
//		
//		automaton.addState(new State("1", true, false));
//		automaton.addTransition("1", "1", "a", "b");
//		
//		System.out.println("After adding another 1");
//		System.out.println(automaton);
	}
}

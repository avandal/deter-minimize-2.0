package com.avandal.determinimize.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.service.AutomatonService;
import com.avandal.determinimize.service.exception.AutomatonException;

@Controller
public class ConsoleController {
	
	@Autowired
	private AutomatonService automatonService;
	
	private ConsoleController() {}
	
	public void run() throws AutomatonException {
		automatonService.mockAutomaton();
		

		System.out.println("Initial automaton:");
		automatonService.getStates().forEach(System.out::println);

		System.out.println("Removing 1");
		automatonService.removeState("1");
		automatonService.getStates().forEach(System.out::println);

		System.out.println("Adding new 1");
		automatonService.addState(new StateDto("1", false, false));
		automatonService.getStates().forEach(System.out::println);
		
		System.out.println("Adding another 1");
//		automatonService.addState(new StateDto("1", true, false));
		automatonService.addTransition("1", "1", "a", "b");
		
		automatonService.getStates().forEach(System.out::println);
	}
}

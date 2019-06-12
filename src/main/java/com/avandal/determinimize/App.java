package com.avandal.determinimize;

import com.avandal.determinimize.model.Automaton;
import com.avandal.determinimize.model.State;

public class App {
	public static void main(String[] args) {
		Automaton automaton = Automaton.getInstance();
		
		automaton.addStates(
				new State("1", true, false),
				new State("2", false, false),
				new State("3", false, true));
		
		automaton.addTransition("1", "2", "a", "b", "c");
		automaton.addTransition("1", "3", "c", "d");
		automaton.addTransition("2", "1", "a");
		
		System.out.println(automaton);
	}
}

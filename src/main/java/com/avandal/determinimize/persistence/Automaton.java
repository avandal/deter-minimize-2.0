package com.avandal.determinimize.persistence;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.avandal.determinimize.model.Link;
import com.avandal.determinimize.model.State;

@Component
public class Automaton {
	private Map<String, State> states;
	
	private static final Logger logger = LoggerFactory.getLogger(Automaton.class);
	
	private Automaton() {
		states = new HashMap<>();
	}
	
	public void addState(State state) {
		if (state == null) {
			throw new IllegalArgumentException("A state can't be null.");
		}
		
		states.put(state.getName(), state);
	}
	
	public void addStates(State...states) {
		for (State state : states) {
			addState(state);
		}
	}
	
	public void addTransition(String sSource, String sTarget, String...transition) {
		State source = states.get(sSource);
		if (source == null) {
			throw new IllegalArgumentException("The state " + sSource + " doesn't exist");
		}
		
		State target = states.get(sTarget);
		if (target == null) {
			throw new IllegalArgumentException("The state " + sTarget + " doesn't exist");
		}
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.addLinkOut(link);
		target.addLinkIn(link);
	}
	
	public void removeTransition(String sSource, String sTarget, String...transition) {
		State source = states.get(sSource);
		if (source == null) {
			throw new IllegalArgumentException("The state " + sSource + " doesn't exist");
		}
		
		State target = states.get(sTarget);
		if (target == null) {
			throw new IllegalArgumentException("The state " + sTarget + " doesn't exist");
		}
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.removeLinkOut(link);
	}
	
	public Map<String, State> getStates() {
		Map<String, State> copy = new HashMap<>();
		states.forEach((key, state) -> copy.put(key, state.copy()));
		return copy;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		states.forEach((key, state) -> sb.append(state + "\n"));
		return sb.toString();
	}
}

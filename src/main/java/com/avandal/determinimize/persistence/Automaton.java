package com.avandal.determinimize.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
			logger.error("A state can't be null.");
			return;
		}
		
		if (states.containsKey(state.getName())) {
			logger.error("The given state already exists.");
			return;
		}
		
		states.put(state.getName(), state);
	}
	
	public void addStates(State...states) {
		for (State state : states) {
			addState(state);
		}
	}
	
	public void addTransition(String sSource, String sTarget, String...transition) {
		if (sSource == null || sTarget == null || transition == null) {
			logger.error("There is a null entry.");
			return;
		}
		
		State source = states.get(sSource);
		if (source == null) {
			logger.error("The state " + sSource + " doesn't exist");
			return;
		}
		
		State target = states.get(sTarget);
		if (target == null) {
			logger.error("The state " + sTarget + " doesn't exist");
			return;
		}
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.addLinkOut(link);
		target.addLinkIn(link);
	}
	
	public void removeTransition(String sSource, String sTarget, String...transition) {
		if (sSource == null || sTarget == null || transition == null) {
			logger.error("There is a null entry.");
			return;
		}
		
		State source = states.get(sSource);
		if (source == null) {
			logger.error("The state " + sSource + " doesn't exist");
			return;
		}
		
		State target = states.get(sTarget);
		if (target == null) {
			logger.error("The state " + sTarget + " doesn't exist");
			return;
		}
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.removeLinkOut(link);
	}
	
	public void removeState(String sState) {
		if (sState == null) {
			logger.error("Can't remove a null state.");
			return;
		}
		
		State state = states.get(sState);
		if (state == null) {
			logger.error("This state doesn't exist.");
			return;
		}
		
		state.removeLinks();
		
		states.remove(sState);
	}
	
	public void removeStates(String...states) {
		for (String state : states) {
			removeState(state);
		}
	}
	
	public Map<String, State> getStates() {
		Map<String, State> copy = new HashMap<>();
		states.forEach((key, state) -> copy.put(key, state.copy()));
		return copy;
	}
	
	public Optional<State> getState(String name) {
		return Optional.ofNullable(states.get(name));
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		states.forEach((key, state) -> sb.append(state + "\n"));
		return sb.toString();
	}
}

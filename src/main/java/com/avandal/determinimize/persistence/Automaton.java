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
		states.put(state.getName(), state);
	}
	
	public void addTransition(String sSource, String sTarget, String...transition) {
		State source = states.get(sSource);
		State target = states.get(sTarget);
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.addLinkOut(link);
		target.addLinkIn(link);
	}
	
	public void removeTransition(String sSource, String sTarget, String...transition) {
		State source = states.get(sSource);
		State target = states.get(sTarget);
		
		Link link = new Link(source, target);
		link.addTransition(transition);
		
		source.removeLinkOut(link);
		target.removeLinkIn(link);
	}
	
	public void removeState(String sState) {
		State state = states.get(sState);
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

package com.avandal.determinimize.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class State {
	private enum InOut { IN, OUT };
	
	private int x;
	private int y;
	
	private String name;
	private Set<Link> linksOut;
	private Set<Link> linksIn;
	
	private boolean initialState;
	private boolean finalState;
	
	private static final Logger logger = LoggerFactory.getLogger(State.class);
	
	public State(String name, boolean initialState, boolean finalState) {
		if (name == null || name.trim().equals("")) {
			throw new IllegalArgumentException("A state must have a name.");
		}

		this.name = name;
		this.initialState = initialState;
		this.finalState = finalState;
		
		this.x = 0;
		this.y = 0;
		
		this.linksOut = new HashSet<>();
		this.linksIn = new HashSet<>();
	}
	
	public State(int x, int y, String name, boolean initialState, boolean finalState) {
		this(name, initialState, finalState);
		
		this.x = x;
		this.y = y;
	}
	
	private Set<Link> _chooseLinks(InOut inOut) {
		switch (inOut) {
		case IN : return linksIn;
		case OUT : return linksOut;
		}
		return linksOut;
	}
	
	private Set<Link> _linksCopy(InOut inOut) {
		Set<Link> copy = new HashSet<Link>();
		_chooseLinks(inOut).forEach(l -> copy.add(l.copy()));
		return copy;
	}
	
	public State copy() {
		State copy = new State(x, y, name, initialState, finalState);
		copy.linksOut = _linksCopy(InOut.OUT);
		copy.linksIn = _linksCopy(InOut.IN);
		
		return copy;
	}
	
	private State _sourceOrTarget(Link link, InOut inOut) {
		return (inOut == InOut.OUT ? link.getTarget() : link.getSource());
	}
	
	private Optional<Link> _getLink(State state, InOut inOut) {
		for (Link link : _chooseLinks(inOut)) {
			if (_sourceOrTarget(link, inOut).equals(state)) {
				return Optional.of(link);
			}
		}
		return Optional.empty();
	}
	
	private void _addValidLink(Link link, InOut inOut) {
		Optional<Link> optLink = _getLink(_sourceOrTarget(link, inOut), inOut);
		if (optLink.isEmpty()) {
			_chooseLinks(inOut).add(link);
		} else {
			optLink.get().addTransition(link.getTransition());
		}
	}
	
	private void _removeValidLink(Link link, InOut inOut) {
		Optional<Link> optLink = _getLink(_sourceOrTarget(link, inOut), inOut);
		if (optLink.isPresent()) {
			Link lLink = optLink.get();
			lLink.removeTransition(link.getTransition());
			
			if (lLink.getTransition().isEmpty()) {
				_chooseLinks(inOut).remove(lLink);
			}
		}
		optLink.ifPresent(l -> l.removeTransition(link.getTransition()));
	}
	
	public void addLinkOut(Link link) {
		if (link != null && this.equals(link.getSource()) && link.getTarget() != null) {
			_addValidLink(link, InOut.OUT);
		} else {
			logger.error("The given link is invalid: {}", link);
		}
	}
	
	public void addLinkIn(Link link) {
		if (link != null && this.equals(link.getTarget()) && link.getSource() != null) {
			_addValidLink(link, InOut.IN);
		} else {
			logger.error("The given link is invalid: {}", link);
		}
	}
	
	public void removeLinkOut(Link link) {
		if (link != null && this.equals(link.getSource()) && link.getTarget() != null) {
			_removeValidLink(link, InOut.OUT);
		} else {
			logger.error("The given link is invalid: {}", link);
		}
	}
	
	public void removeLinkIn(Link link) {
		if (link != null && this.equals(link.getTarget()) && link.getSource() != null) {
			_removeValidLink(link, InOut.IN);
		} else {
			logger.error("The given link is invalid: {}", link);
		}
	}
	
	private void _removeLinks(String name, InOut inOut) {
		switch (inOut) {
		case IN : 
			linksIn = linksIn.stream().filter(link -> !link.getSource().getName().equals(name)).collect(Collectors.toSet());
			break;
			
		case OUT :
			linksOut = linksOut.stream().filter(link -> !link.getTarget().getName().equals(name)).collect(Collectors.toSet());
			break;
		}
	}
	
	public void removeLinks() {
		linksOut.forEach(link -> link.getTarget()._removeLinks(name, InOut.IN));
		linksIn.forEach(link -> link.getSource()._removeLinks(name, InOut.OUT));
	}
	
	@Override
	public String toString() {
		return "State: " + this.name + "[initial=" + initialState + ", final=" + finalState + ", linksOut=" + linksOut + ", linksIn=" + linksIn + "]";
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInitialState() {
		return initialState;
	}

	public void setInitialState(boolean initialState) {
		this.initialState = initialState;
	}

	public boolean isFinalState() {
		return finalState;
	}

	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
	}
	
	public Set<Link> getLinksOut() {
		return _linksCopy(InOut.OUT);
	}
	
	public Set<Link> getLinksIn() {
		return _linksCopy(InOut.IN);
	}

	@Override
	public int hashCode() {
		return Objects.hash(finalState, initialState, linksOut, name, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		return finalState == other.finalState && initialState == other.initialState
				&& Objects.equals(linksOut, other.linksOut) && Objects.equals(linksIn, other.linksIn) 
				&& Objects.equals(name, other.name) && x == other.x && y == other.y;
	}
	
	
}

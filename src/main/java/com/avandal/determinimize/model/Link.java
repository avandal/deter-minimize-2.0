package com.avandal.determinimize.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Link {
	private State source;
	private State target;
	private Set<String> transition;
	
	private int curve;
	
	private static final Logger logger = LoggerFactory.getLogger(Link.class);
	
	public Link(State source, State target) {
		this(source, target, 30);
	}
	
	public Link(State source, State target, int curve) {
		if (source == null || target == null) {
			throw new IllegalArgumentException("The source or the target can't be null.");
		}
		
		this.source = source;
		this.target = target;
		this.transition = new HashSet<>();
		
		this.curve = curve;
	}
	
	public Link copy() {
		Link copy = new Link(source, target, curve);
		copy.transition.addAll(transition);
		return copy;
	}
	
	public void addTransition(String transition) {
		this.transition.add(transition);
	}
	
	public void addTransition(Collection<String> transition) {
		this.transition.addAll(transition);
	}
	
	public void addTransition(String...transition) {
		addTransition(Arrays.asList(transition));
	}
	
	public void removeTransition(String transition) {
		this.transition.remove(transition);
	}
	
	public void removeTransition(Collection<String> transition) {
		for (String s : transition) {
			this.transition.remove(s);
		}
	}
	
	public void removeTransition(String...transition) {
		removeTransition(Arrays.asList(transition));
	}
	
	@Override
	public String toString() {
		return "Link: " + this.source.getName() + " --" + this.transition + "{curve=" + this.curve + "}-> " + this.target.getName();
	}
	

	public State getSource() {
		return source;
	}

	public void setSource(State source) {
		this.source = source;
	}

	public State getTarget() {
		return target;
	}

	public void setTarget(State target) {
		this.target = target;
	}

	public Set<String> getTransition() {
		return new HashSet<>(transition);
	}
	
	public int getCurve() {
		return curve;
	}
	
	public void setCurve(int curve) {
		this.curve = curve;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Link)) {
			return false;
		}
		Link other = (Link) obj;
		return Objects.equals(source, other.source) && Objects.equals(target, other.target)
				&& Objects.equals(transition, other.transition) && curve == other.curve;
	}
	
	
}

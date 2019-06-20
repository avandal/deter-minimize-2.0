package com.avandal.determinimize.model.builder;

import java.util.List;

import com.avandal.determinimize.model.Link;
import com.avandal.determinimize.model.State;

public class LinkBuilder {
	private State source;
	private State target;
	private List<String> transition;
	
	private int curve;
	
	public LinkBuilder() {}
	
	public LinkBuilder source(State source) {
		this.source = source;
		return this;
	}
	
	public LinkBuilder target(State target) {
		this.target = target;
		return this;
	}
	
	public LinkBuilder transition(List<String> transition) {
		this.transition = transition;
		return this;
	}
	
	public LinkBuilder curve(int curve) {
		this.curve = curve;
		return this;
	}
	
	public Link build() {
		Link ret = new Link(source, target, curve);
		ret.addTransition(transition);
		return ret;
	}
}


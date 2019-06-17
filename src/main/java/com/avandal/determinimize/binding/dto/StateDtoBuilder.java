package com.avandal.determinimize.binding.dto;

import java.util.Set;

public class StateDtoBuilder {
	private int x;
	private int y;
	
	private String name;
	private Set<LinkDto> linksOut;
	private Set<LinkDto> linksIn;
	
	private boolean initialState;
	private boolean finalState;
	
	public StateDtoBuilder() {}
	
	public StateDtoBuilder x(int x) {
		this.x = x;
		return this;
	}
	
	public StateDtoBuilder y(int y) {
		this.y = y;
		return this;
	}
	
	public StateDtoBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public StateDtoBuilder linksOut(Set<LinkDto> linksOut) {
		this.linksOut = linksOut;
		return this;
	}
	
	public StateDtoBuilder linksIn(Set<LinkDto> linksIn) {
		this.linksIn = linksIn;
		return this;
	}
	
	public StateDtoBuilder initialState(boolean initialState) {
		this.initialState = initialState;
		return this;
	}
	
	public StateDtoBuilder finalState(boolean finalState) {
		this.finalState = finalState;
		return this;
	}
	
	public StateDto build() {
		return new StateDto(x, y, name, linksOut, linksIn, initialState, finalState);
	}
}

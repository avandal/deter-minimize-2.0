package com.avandal.determinimize.model.builder;

import com.avandal.determinimize.model.State;

public class StateBuilder {
	private int x;
	private int y;
	
	private String name;
	
	private boolean initialState;
	private boolean finalState;
	
	public StateBuilder() {}
	
	public StateBuilder x(int x) {
		this.x = x;
		return this;
	}
	
	public StateBuilder y(int y) {
		this.y = y;
		return this;
	}
	
	public StateBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public StateBuilder initialState(boolean initialState) {
		this.initialState = initialState;
		return this;
	}
	
	public StateBuilder finalState(boolean finalState) {
		this.finalState = finalState;
		return this;
	}
	
	public State build() {
		return new State(x, y, name, initialState, finalState);
	}
}

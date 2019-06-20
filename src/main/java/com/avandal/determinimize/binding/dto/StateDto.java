package com.avandal.determinimize.binding.dto;

import java.util.HashSet;
import java.util.Set;

public class StateDto {
	private int x;
	private int y;
	
	private String name;
	private Set<LinkDto> linksOut;
	private Set<LinkDto> linksIn;
	
	private boolean initialState;
	private boolean finalState;
	
	public StateDto(int x, int y, String name, Set<LinkDto> linksOut, Set<LinkDto> linksIn, boolean initialState, boolean finalState) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.linksOut = linksOut;
		this.linksIn = linksIn;
		this.initialState = initialState;
		this.finalState = finalState;
	}
	
	public StateDto(String name, boolean initialState, boolean finalState) {
		this(0, 0, name, new HashSet<>(), new HashSet<>(), initialState, finalState);
	}
	
	public StateDto(int x, int y, String name, boolean initialState, boolean finalState) {
		this(x, y, name, new HashSet<>(), new HashSet<>(), initialState, finalState);
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

	public Set<LinkDto> getLinksOut() {
		return linksOut;
	}

	public void setLinksOut(Set<LinkDto> linksOut) {
		this.linksOut = linksOut;
	}

	public Set<LinkDto> getLinksIn() {
		return linksIn;
	}

	public void setLinksIn(Set<LinkDto> linksIn) {
		this.linksIn = linksIn;
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
	
	@Override
	public String toString() {
		return "StateDto: " + this.name + "[x=" + x + ", y=" + y + ", initial=" + initialState + ", final=" + finalState + ", linksOut=" + linksOut + ", linksIn=" + linksIn + "]";
	}
}

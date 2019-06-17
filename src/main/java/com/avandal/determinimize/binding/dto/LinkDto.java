package com.avandal.determinimize.binding.dto;

public class LinkDto {
	private StateDto source;
	private StateDto target;
	private String[] transition;
	
	public LinkDto(StateDto source, StateDto target, String[] transition) {
		this.source = source;
		this.target = target;
		this.transition = transition;
	}

	public StateDto getSource() {
		return source;
	}

	public void setSource(StateDto source) {
		this.source = source;
	}

	public StateDto getTarget() {
		return target;
	}

	public void setTarget(StateDto target) {
		this.target = target;
	}

	public String[] getTransition() {
		return transition;
	}

	public void setTransition(String[] transition) {
		this.transition = transition;
	}
}

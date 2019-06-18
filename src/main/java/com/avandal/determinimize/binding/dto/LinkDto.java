package com.avandal.determinimize.binding.dto;

import java.util.List;

public class LinkDto {
	private StateDto source;
	private StateDto target;
	private List<String> transition;
	
	public LinkDto(StateDto source, StateDto target, List<String> transition) {
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

	public List<String> getTransition() {
		return transition;
	}

	public void setTransition(List<String> transition) {
		this.transition = transition;
	}
	
	@Override
	public String toString() {
		return "LinkDto: " + this.source.getName() + " --" + this.transition + "-> " + this.target.getName();
	}
}

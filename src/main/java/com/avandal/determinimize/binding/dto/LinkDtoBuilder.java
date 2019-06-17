package com.avandal.determinimize.binding.dto;

public class LinkDtoBuilder {
	private StateDto source;
	private StateDto target;
	private String[] transition;
	
	public LinkDtoBuilder() {}
	
	public LinkDtoBuilder source(StateDto source) {
		this.source = source;
		return this;
	}
	
	public LinkDtoBuilder target(StateDto target) {
		this.target = target;
		return this;
	}
	
	public LinkDtoBuilder transition(String[] transition) {
		this.transition = transition;
		return this;
	}
	
	public LinkDto build() {
		return new LinkDto(source, target, transition);
	}
}

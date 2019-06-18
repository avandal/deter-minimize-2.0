package com.avandal.determinimize.service.exception;

public enum FailReason {
	NULL_ARGUMENT("A given argument is null."),
	
	NULL_STATE("A state cannot be null."),
	EMPTY_STATE_NAME("A state cannot have a null or empty name."),
	STATE_ALREADY_EXISTS("The given state already exists."),
	STATE_DOESNT_EXIST("The given state doesn't exist.")
	
	;
	
	private String value;
	
	FailReason(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}

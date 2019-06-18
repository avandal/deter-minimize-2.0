package com.avandal.determinimize.service.exception;

public class AutomatonException extends Exception {
	private static final long serialVersionUID = 821281092190513075L;
	
	public AutomatonException(FailReason reason) {
		super(reason.getValue());
	}
	
	public AutomatonException(FailReason reason, Object state) {
		super(state + " -> " + reason.getValue());
	}
}

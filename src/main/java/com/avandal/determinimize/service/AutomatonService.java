package com.avandal.determinimize.service;

import java.awt.Dimension;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.binding.mapper.StateMapper;
import com.avandal.determinimize.model.State;
import com.avandal.determinimize.persistence.Automaton;
import com.avandal.determinimize.service.exception.AutomatonException;
import com.avandal.determinimize.service.exception.FailReason;

@Service
public class AutomatonService {
	
	private Logger logger = LoggerFactory.getLogger(AutomatonService.class);
	
	@Autowired
	private Automaton automaton;
	
	@Autowired
	private StateMapper stateMapper;
	
	private AutomatonService() {}
	
	public void clearAutomaton() {
		automaton.clear();
	}
	
	public List<StateDto> getStates() {
		return automaton.getStates().values().stream().map(stateMapper::stateToDto).collect(Collectors.toList());
	}
	
	public void addState(StateDto state) throws AutomatonException {
		if (state == null) {
			throw new AutomatonException(FailReason.NULL_STATE);
		}
		if (state.getName() == null || state.getName().trim().equals("")) {
			throw new AutomatonException(FailReason.EMPTY_STATE_NAME);
		}
		if (automaton.getState(state.getName()).isPresent()) {
			throw new AutomatonException(FailReason.STATE_ALREADY_EXISTS, state);
		}
		automaton.addState(stateMapper.dtoToState(state));
	}
	
	public void addStates(StateDto...states) {
		for (StateDto state : states) {
			try {
				addState(state);
			} catch (AutomatonException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addTransition(String source, String target, int curve, String...transition) throws AutomatonException {
		try {
			_checkTransition(source, target, curve, transition);
		} catch (AutomatonException e) {
			throw e;
		}
		
		automaton.addTransition(source, target, curve, transition);
	}
	
	public void addTransition(String source, String target, String...transition) throws AutomatonException {
		addTransition(source, target, 30, transition);
	}
	
	public void removeState(String state) throws AutomatonException {
		if (state == null || state.trim().equals("")) {
			throw new AutomatonException(FailReason.NULL_ARGUMENT);
		}
		
		if (automaton.getState(state).isEmpty()) {
			throw new AutomatonException(FailReason.STATE_DOESNT_EXIST, state);
		}
		
		automaton.removeState(state);
	}
	
	public void removeStates(String...states) {
		for (String state : states) {
			try {
				removeState(state);
			} catch (AutomatonException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void removeTransition(String source, String target, String...transition) throws AutomatonException {
		try {
			_checkTransition(source, target, 0, transition);
		} catch (AutomatonException e) {
			throw e;
		}
		
		automaton.removeTransition(source, target, transition);
	}
	
	public Dimension getMaxDimension() {
		return automaton.getMaxDimension();
	}
	
	private void _checkTransition(String source, String target, int curve, String...transition) throws AutomatonException {
		if (source == null || source.trim().equals("") 
		 || target == null || target.trim().equals("")
		 || transition == null) {
			throw new AutomatonException(FailReason.NULL_ARGUMENT);
		}
		
		for (String t : transition) {
			if (t == null || t.trim().equals("")) {
				throw new AutomatonException(FailReason.NULL_ARGUMENT);
			}
		}
		
		if (automaton.getState(source).isEmpty()) {
			throw new AutomatonException(FailReason.STATE_DOESNT_EXIST, source);
		}
		
		if (automaton.getState(target).isEmpty()) {
			throw new AutomatonException(FailReason.STATE_DOESNT_EXIST, target);
		}
	}
}

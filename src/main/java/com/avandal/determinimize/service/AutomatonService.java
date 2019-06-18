package com.avandal.determinimize.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.binding.mapper.StateMapper;
import com.avandal.determinimize.model.State;
import com.avandal.determinimize.persistence.Automaton;

@Service
public class AutomatonService {
	
	@Autowired
	private Automaton automaton;
	
	@Autowired
	private StateMapper stateMapper;
	
	private AutomatonService() {}
	
	public void mockAutomaton() {
		automaton.addStates(
				new State("1", true, false),
				new State("2", false, false),
				new State("3", false, true));
		
		automaton.addTransition("1", "2", "a", "b", "c");
		automaton.addTransition("1", "3", "c", "d");
		automaton.addTransition("2", "1", "a");
		automaton.addTransition("1", "2", "c", "e");
		automaton.addTransition("2", "3", "a");
	}
	
	public List<StateDto> getStates() {
		return automaton.getStates().values().stream().map(stateMapper::stateToDto).collect(Collectors.toList());
	}
}

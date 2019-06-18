package com.avandal.determinimize.binding.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.binding.dto.builder.StateDtoBuilder;
import com.avandal.determinimize.model.State;
import com.avandal.determinimize.model.builder.StateBuilder;

@Component
public class StateMapper {
	@Autowired
	private LinkMapper linkMapper;
	
	private StateMapper() {}
	
	StateDto stateToDtoWithoutLinks(State state) {
		return new StateDtoBuilder()
				.x(state.getX())
				.y(state.getY())
				.name(state.getName())
				.initialState(state.isInitialState())
				.finalState(state.isFinalState())
				.build();
	}
	
	State dtoToStateWithoutLinks(StateDto state) {
		return new StateBuilder()
				.x(state.getX())
				.y(state.getY())
				.name(state.getName())
				.initialState(state.isInitialState())
				.finalState(state.isFinalState())
				.build();
	}
	
	public StateDto stateToDto(State state) {
		return new StateDtoBuilder()
				.x(state.getX())
				.y(state.getY())
				.name(state.getName())
				.linksOut(state.getLinksOut().stream().map(link -> linkMapper.linkToDto(link)).collect(Collectors.toSet()))
				.linksIn(state.getLinksIn().stream().map(link -> linkMapper.linkToDto(link)).collect(Collectors.toSet()))
				.initialState(state.isInitialState())
				.finalState(state.isFinalState())
				.build();
	}
	
	public State dtoToState(StateDto state) {
		return new StateBuilder()
				.x(state.getX())
				.y(state.getY())
				.name(state.getName())
				.initialState(state.isInitialState())
				.finalState(state.isFinalState())
				.build();
	}
}

package com.avandal.determinimize.binding.mapper;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avandal.determinimize.binding.dto.LinkDto;
import com.avandal.determinimize.binding.dto.builder.LinkDtoBuilder;
import com.avandal.determinimize.model.Link;

@Component
public class LinkMapper {
	
	@Autowired
	StateMapper stateMapper;
	
	public LinkDto linkToDto(Link link) {
		return new LinkDtoBuilder()
				.source(stateMapper.stateToDtoWithoutLinks(link.getSource()))
				.target(stateMapper.stateToDtoWithoutLinks(link.getTarget()))
				.transition(Arrays.asList(link.getTransition().toArray(new String[0])))
				.curve(link.getCurve())
				.build();
	}
}

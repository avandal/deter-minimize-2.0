package com.avandal.determinimize.binding.parser;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.service.AutomatonService;
import com.avandal.determinimize.service.exception.AutomatonException;

@Component
public class DmndotParser {
	private static final int STATE_NAME = 1;
	private static final int STATE_X = 2;
	private static final int STATE_Y = 3;
	private static final int STATE_INITIAL = 4;
	private static final int STATE_FINAL = 5;
	
	private static final int LINK_SOURCE = 1;
	private static final int LINK_TARGET = 5;
	private static final int LINK_TRANSITION = 2;
	private static final int LINK_CURVE = 4;
	
	@Autowired
	private AutomatonService automatonService;
	
	private String statePattern = "([a-zA-Z0-9_,]+)\\[x=([0-9]+), ?y=([0-9]+), ?initial=(true|false), ?final=(true|false)\\];";
	private String linkPattern = "([a-zA-Z0-9_,]+) \\-\\-\\{([a-zA-Z0-9:=/ ]+(, ?[a-zA-Z0-9:=/ ])*)\\}\\[curve=(\\-?[0-9]+)\\]\\-\\-> ([a-zA-Z0-9_,]+);";
	
	private DmndotParser() {}
	
	public List<StateDto> open(String filename) throws IOException {
		automatonService.clearAutomaton();
		Files.lines(Paths.get(filename), StandardCharsets.UTF_8).forEach(line -> {
			Matcher m;
			
			if ((m = Pattern.compile(statePattern).matcher(line)).matches()) {
				_addState(m);
			} else if ((m = Pattern.compile(linkPattern).matcher(line)).matches()) {
				_addLink(m);
			}
		});
		
		return automatonService.getStates();
	}

	private void _addLink(Matcher m) {
		try {
			automatonService.addTransition(
				m.group(LINK_SOURCE),
				m.group(LINK_TARGET),
				Integer.parseInt(m.group(LINK_CURVE)),
				m.group(LINK_TRANSITION).split(",")
			);
		} catch (AutomatonException e) {
			e.printStackTrace();
		}
	}

	private void _addState(Matcher m) {
		try {
			automatonService.addState(new StateDto(
				Integer.parseInt(m.group(STATE_X)),
				Integer.parseInt(m.group(STATE_Y)),
				m.group(STATE_NAME),
				Boolean.parseBoolean(m.group(STATE_INITIAL)),
				Boolean.parseBoolean(m.group(STATE_FINAL))
			));
		} catch (AutomatonException e) {
			e.printStackTrace();
		}
	}
}

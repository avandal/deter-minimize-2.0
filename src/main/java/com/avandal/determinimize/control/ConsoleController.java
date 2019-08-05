package com.avandal.determinimize.control;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.binding.parser.DmndotParser;
import com.avandal.determinimize.service.AutomatonService;
import com.avandal.determinimize.service.exception.AutomatonException;

@Controller
public class ConsoleController {
	
	@Autowired
	private AutomatonService automatonService;
	
	@Autowired
	private DmndotParser parser;
	
	private ConsoleController() {}
	
	public void run() throws AutomatonException {
		// parser.open("/home/excilys/eclipse-workspace/deter-minimize/graphs/graph3.grv");
	}
}

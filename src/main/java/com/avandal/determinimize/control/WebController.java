package com.avandal.determinimize.control;

import java.awt.Dimension;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avandal.determinimize.binding.dto.StateDto;
import com.avandal.determinimize.binding.parser.DmndotParser;
import com.avandal.determinimize.service.AutomatonService;

@RestController
@RequestMapping("/deter-minimize-2.0/")
@CrossOrigin(origins = "*", methods = { RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })

public class WebController {
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private AutomatonService automatonService;
	
	@Autowired
	private DmndotParser parser;

	@PutMapping("open")
	public ResponseEntity<List<StateDto>> open(@RequestBody String url) {
		try {
			parser.open(url);
//			for (StateDto state : automatonService.getStates()) {
//				System.out.println(state);
//			}
			return new ResponseEntity<>(automatonService.getStates(), HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("states/dimension")
	public ResponseEntity<Dimension> getMaxDimension() {
		return new ResponseEntity<>(automatonService.getMaxDimension(), HttpStatus.OK);
	}
}

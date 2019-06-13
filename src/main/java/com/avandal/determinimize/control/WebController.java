package com.avandal.determinimize.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
public class WebController {
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping
	public ResponseEntity<String> get() {
		return new ResponseEntity<>("Coucou !", HttpStatus.OK);
	}
}

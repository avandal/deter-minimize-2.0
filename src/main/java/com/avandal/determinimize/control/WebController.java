package com.avandal.determinimize.control;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deter-minimize-2.0/")
public class WebController {

	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping
	public ResponseEntity<List<String>> get() {
		String[] arr = new String[] { "Coucou", "toi !" };
		return new ResponseEntity<>(Arrays.asList(arr), HttpStatus.OK);
	}
}

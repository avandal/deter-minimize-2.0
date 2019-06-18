package com.avandal.determinimize.control;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*", methods = { RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
public class WebController {
	
	private class StringObject implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5835202027878025252L;
		private String value;
		
		public StringObject(String value) {
			this.value = value;
		}
		
		@SuppressWarnings("unused")
		public String getValue() {
			return value;
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping
	public ResponseEntity<String> get() {
		try {
			String[] arr = new String[] {"Coucou", "toi !"};
			return new ResponseEntity<String>(new ObjectMapper().writeValueAsString(Arrays.asList(arr)), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

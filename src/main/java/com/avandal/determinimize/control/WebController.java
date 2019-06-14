package com.avandal.determinimize.control;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		public String getValue() {
			return value;
		}
	}
	
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);

	@GetMapping
	@ResponseBody
	public ResponseEntity<String> get() {
//		Map<String, String> map = new HashMap<>();
//		map.put("value", "Coucou !");
		try {
			return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new StringObject("Coucou !")), HttpStatus.OK);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

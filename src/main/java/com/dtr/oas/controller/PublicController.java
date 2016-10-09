package com.dtr.oas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public/api")
public class PublicController {


	@RequestMapping(value="/test", method = RequestMethod.GET)
	public ResponseEntity getTest() {
		return new ResponseEntity("hello world",HttpStatus.OK);
	}

}

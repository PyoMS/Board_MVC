package com.ms.web.error.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
	private static final Logger logger = LoggerFactory.getLogger(NotFoundException.class);
	
	@ExceptionHandler(RuntimeException.class)
	public String notFoundException(Model model, Exception e) {
		logger.info("@notFoundException ¹æ½Ä \n###exeption : " + e.getMessage());
		model.addAttribute("exception", e);
		return "error/404error";
	}
}

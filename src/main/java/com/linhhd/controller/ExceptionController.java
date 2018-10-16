package com.linhhd.controller;

import org.apache.log4j.Logger;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
	private static Logger logger = Logger.getLogger(ExceptionController.class);
	
	@ExceptionHandler(value={NoHandlerFoundException.class, JDBCConnectionException.class})
	public String exceptionHandler(Exception exception){
		logger.error(exception);
		return "404";
	}
	
	@ExceptionHandler(value={Exception.class})
	public String exceptionAll(Exception exception){
		logger.error(exception);
		return "error";
	}
}

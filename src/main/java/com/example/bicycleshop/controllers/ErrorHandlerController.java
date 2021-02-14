package com.example.bicycleshop.controllers;

import com.example.bicycleshop.exceptions.NotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandlerController {
	private Log logger = LogFactory.getLog(ErrorHandlerController.class);
	
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String notFoundHandler(NotFoundException exception) {
		return exception.getMessage();
	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public String handleException(HttpServletRequest request, Exception exception, Model model){
		logger.error(request.getRequestURL(), exception);
		model.addAttribute("title", "Błąd");
		HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
		model.addAttribute("message", error.value() + " - " + error.getReasonPhrase());
		return "message-view";
	}
}

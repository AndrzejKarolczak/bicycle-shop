package com.example.bicycleshop.controllers;

import com.example.bicycleshop.exceptions.AccountAlreadyExists;
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
	private final Log logger = LogFactory.getLog(ErrorHandlerController.class);
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public String notFoundHandler(HttpServletRequest request, NotFoundException exception, Model model) {
		logger.error(request.getRequestURL(), exception);
		model.addAttribute("title", "Błąd");
		model.addAttribute("message", HttpStatus.NOT_FOUND.value() + ": " + exception.getMessage());
		return "message-view";
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
	
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(AccountAlreadyExists.class)
	public String handleAccountAlreadyExistException(HttpServletRequest request, AccountAlreadyExists exception, Model model){
		logger.info(request.getRequestURL(), exception);
		model.addAttribute("title", "Błąd");
		model.addAttribute("message", HttpStatus.CONFLICT.value() + ": " + exception.getMessage());
		return "message-view";
	}
}

package com.automotora.exceptions;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.automotora.responses.ErrorMessageResponse;


@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class HandlerException {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorMessageResponse notFoundException(NotFoundException ex) {
		ErrorMessageResponse errorMessage = new ErrorMessageResponse();
		errorMessage.setInternalMessage(ex.getMessage());
		errorMessage.setUserMessage(ex.getUserMessage());
		errorMessage.setErrorType(ex.getErrorType());
		return errorMessage;
	}

	@ExceptionHandler(NotAllowedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorMessageResponse malFormedRequestException(NotAllowedException ex) {
		ErrorMessageResponse errorMessage = new ErrorMessageResponse();
		errorMessage.setInternalMessage(ex.getMessage());
		errorMessage.setUserMessage(ex.getUserMessage());
		errorMessage.setErrorType(ex.getErrorType());
		return errorMessage;
	}
	
}

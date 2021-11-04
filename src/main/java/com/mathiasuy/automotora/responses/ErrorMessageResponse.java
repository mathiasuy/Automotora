package com.mathiasuy.automotora.responses;

import java.time.LocalDateTime;

import com.mathiasuy.automotora.exceptions.ErrorType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ErrorMessageResponse {

	private String internalMessage;
	private String userMessage;
	private ErrorType errorType;
	private LocalDateTime date;
		
	public ErrorMessageResponse() {
		this.setDate(LocalDateTime.now());
	}
	
}

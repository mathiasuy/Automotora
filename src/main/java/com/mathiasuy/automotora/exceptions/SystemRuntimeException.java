package com.automotora.exceptions;

import org.slf4j.Logger;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SystemRuntimeException extends RuntimeException{

	private static final long serialVersionUID = -7028961099476581941L;

	private static final String GENERAL_ERROR = "Ha ocurrido un error";
	private String userMessage;
	private ErrorType errorType;
	private Logger logger;
	
    public SystemRuntimeException(ErrorType errorType, String userMessage, Logger logger, String internalMessage) {
        super(userMessage);
        this.userMessage = userMessage;
        this.errorType = errorType;
        this.logger = logger;
        logger.error(internalMessage);
    }
    
    public SystemRuntimeException(ErrorType errorType, Throwable cause, String userMessage, Logger logger, String internalMessage) {
    	super(userMessage);
        this.userMessage = userMessage;
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(internalMessage, cause);
    }
    
    public SystemRuntimeException(ErrorType errorType, Logger logger, String internalMessage) {
    	super(GENERAL_ERROR);
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(internalMessage);
    }
    
    public SystemRuntimeException(ErrorType errorType, Throwable cause, Logger logger, String internalMessage) {
    	super(GENERAL_ERROR);
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(internalMessage, cause);
    }
    
    public SystemRuntimeException(ErrorType errorType, Logger logger) {
    	super(GENERAL_ERROR);
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(GENERAL_ERROR);
    }
    
    public SystemRuntimeException(ErrorType errorType, Throwable cause, Logger logger) {
    	super(GENERAL_ERROR);
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(GENERAL_ERROR, cause);
    }
    
    
    public SystemRuntimeException(ErrorType errorType, String userMessage, Logger logger) {
    	super(userMessage);
        this.userMessage = userMessage;
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(userMessage);
    }
    
    public SystemRuntimeException(ErrorType errorType, Throwable cause, String userMessage, Logger logger) {
    	super(userMessage);
        this.userMessage = userMessage;
        this.errorType = errorType;
        this.logger = logger;
    	logger.error(userMessage, cause);
    }
    
}

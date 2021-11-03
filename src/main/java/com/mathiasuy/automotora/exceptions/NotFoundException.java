package com.mathiasuy.automotora.exceptions;

import org.slf4j.Logger;

public class NotFoundException extends SystemRuntimeException {

	private static final long serialVersionUID = 3430372500179878540L;

	public NotFoundException(ErrorType errorType, String userMessage, Logger logger, String internalMessage) {
        super(errorType, userMessage, logger, internalMessage);
    }
    
    public NotFoundException(ErrorType errorType, Throwable cause, String userMessage, Logger logger, String internalMessage) {
        super(errorType, cause, userMessage, logger, internalMessage);
    }
    
    public NotFoundException(ErrorType errorType, Logger logger, String internalMessage) {
        super(errorType, logger, internalMessage);
    }
    
    public NotFoundException(ErrorType errorType, Throwable cause, Logger logger, String internalMessage) {
        super(errorType, cause, logger, internalMessage);
    }
    
    public NotFoundException(ErrorType errorType, Logger logger) {
        super(errorType, logger);
    }
    
    public NotFoundException(ErrorType errorType, Throwable cause, Logger logger) {
        super(errorType, cause, logger);
    }
    
    
    public NotFoundException(ErrorType errorType, String userMessage, Logger logger) {
        super(errorType, userMessage, logger);
    }
    
    public NotFoundException(ErrorType errorType, Throwable cause, String userMessage, Logger logger) {
        super(errorType, cause, userMessage, logger);
    }	
	
}

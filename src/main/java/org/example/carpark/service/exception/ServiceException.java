package org.example.carpark.service.exception;

import org.example.carpark.exception.ApplicationException;
import utils.ErrorMessages;

public class ServiceException extends ApplicationException {
    public ServiceException(){
        super(ErrorMessages.SERVICE_ERROR);
    }

    public ServiceException(Exception cause) {
        super(ErrorMessages.SERVICE_ERROR, cause);
    }

    public ServiceException(String messageKey) {
        super(messageKey);
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}

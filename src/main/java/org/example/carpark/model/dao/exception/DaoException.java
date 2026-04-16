package org.example.carpark.model.dao.exception;

import org.example.carpark.exception.ApplicationException;
import utils.ErrorMessages;

public class DaoException extends ApplicationException {

    public DaoException(){
        super(ErrorMessages.DAO_ERROR);
    }

    public DaoException(Exception cause) {

        super(ErrorMessages.DAO_ERROR, cause);
    }
}


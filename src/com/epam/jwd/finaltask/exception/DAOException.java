package com.epam.jwd.finaltask.exception;

/**
 * DAOException is used to throw an exception when manipulate data in DAO
 */
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }
}

package com.ATMProject.atm.exception;


public class ATMException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public ATMException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}

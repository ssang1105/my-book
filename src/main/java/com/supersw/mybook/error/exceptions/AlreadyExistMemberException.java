package com.supersw.mybook.error.exceptions;

public class AlreadyExistMemberException extends RuntimeException {
    private static final long serialVersionUID = 2622515567353191285L;

    public AlreadyExistMemberException(String message) {
        super(message);
    }
}

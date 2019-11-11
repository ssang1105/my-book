package com.supersw.mybook.error.exceptions;

public class NotExistMemberException extends RuntimeException {

    private static final long serialVersionUID = 9172107237789454406L;

    public NotExistMemberException(String message) {
        super(message);
    }

}

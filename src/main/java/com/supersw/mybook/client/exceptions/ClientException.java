package com.supersw.mybook.client.exceptions;

public class ClientException extends RuntimeException {

    private static final long serialVersionUID = 8256583033452927847L;

    public ClientException(int statusCode, String request, String response) {
        super("statusCode: " + statusCode + "\nrequest: " + request + "\nresponse: " + response);
    }

    public ClientException(String message, Throwable t) {
        super(message, t);
    }
}

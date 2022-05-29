package com.sprincsecurity.resource.exception;

public class ResourceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceException(String msg) {
        super(msg);
    }

    public ResourceException(String msg, Throwable cause) {
        super(msg,cause);
    }
}

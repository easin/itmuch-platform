package com.itmuch.core.exception;

public class ParamInvalidException extends IllegalArgumentException {
    private static final long serialVersionUID = -290372483676756273L;

    public ParamInvalidException() {
    }

    public ParamInvalidException(String message) {
        super(message);
    }
}

package br.com.api.f1.exception;

public class BaseException extends RuntimeException {

    public BaseException(final String message) {
        super(message);
    }

    public BaseException(final String message, final Throwable cause) {
        super(message, cause);
    }

}

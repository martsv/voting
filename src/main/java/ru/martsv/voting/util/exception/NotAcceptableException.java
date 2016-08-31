package ru.martsv.voting.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * mart
 * 31.08.2016
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Not aceptable request")  // 406
public class NotAcceptableException extends RuntimeException {
    public NotAcceptableException(String message) {
        super(message);
    }
}


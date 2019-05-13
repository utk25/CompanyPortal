package com.portal.company.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UserLoggedOutException extends RuntimeException {
    public UserLoggedOutException(String message) {
        super(message);
    }
}

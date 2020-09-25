package com.sapient.problem.weatherforecast.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * ServiceInvocationException class for exception occurred during any service to service
 * communication across ATP.
 */
public class ServiceInvocationException extends ResponseStatusException {

    /**
     * Constructor with a response status and a reason to add to the exception message as
     * explanation, as well as a nested exception.
     *
     * @param status the HTTP status (required)
     * @param reason the associated reason (optional)
     * @param cause a nested exception (optional)
     */
    public ServiceInvocationException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}

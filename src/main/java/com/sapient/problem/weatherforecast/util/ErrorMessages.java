package com.sapient.problem.weatherforecast.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
@RequiredArgsConstructor
public class ErrorMessages {

    private final Environment environment;

    public static final String EXCEPTION_KEY = "exception.key";
    public static final String MESSAGE_KEY = "message.key";
    public static final String ERROR_KEY = "error.key";
    public static final String STATUS_KEY = "status.key";

    public static final String REQ_VALIDATION_ERROR = "request.validation.error";
    public static final String SYSTEM_ERROR = "system.error";
    public static final String UNKNOWN_ERROR = "unknown.error";

    /**
     * This method will provide set properties value based on provided key. if value key is not
     * found then it will return empty string.
     **/
    public String getMessage(String key) {
        var property = environment.getProperty(key);
        return property != null ? property : "";
    }

    public String getExceptionKey() {
        return getMessage(EXCEPTION_KEY);
    }

    public String getMessageKey() {
        return getMessage(MESSAGE_KEY);
    }

    public String getErrorKey() {
        return getMessage(ERROR_KEY);
    }

    public String getStatusKey() {
        return getMessage(STATUS_KEY);
    }

    public String getReqValidationError() {
        return getMessage(REQ_VALIDATION_ERROR);
    }

    public String getSystemError() {
        return getMessage(SYSTEM_ERROR);
    }

    public String getUnknownError() {
        return getMessage(UNKNOWN_ERROR);
    }
}
